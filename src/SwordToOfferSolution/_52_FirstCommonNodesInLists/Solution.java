package SwordToOfferSolution._52_FirstCommonNodesInLists;

/*
 * 两个链表的第一个公共节点
 *
 * 题目描述：
 * 输入两个链表，找出它们的第一个公共结点。
 *
 * 思路一：
 * 1. 既然是两个链表的第一个公共节点，则说明这两个链表最终会像 “Y” 的形状；
 * 2. 可以使用两个辅助栈，分别将链表灌到栈里后，依次出栈；
 * 3. 比较栈顶的元素，直到找到最后一个相同的节点。
 *    也就是所，如果发现元素不同的话，则上一次出栈的元素就是这两个链表的第一个公共节点。
 * 缺点：使用额外的栈空间。
 *
 * 思路二：
 * 1. 首先计算两个链表的长度之差为 d；
 * 2. 然后让短的链表先走 d 步，此时再让两个链表同时移动，在移动的过程中比较节点大小。
 *    如果相等，则找到了第一个公共节点。
 * 3. 但需要注意的是，如果在相遇节点之前，两个链表中有相同的节点，则需要判断 longList != shortList，
 *    即需要判断引用，而不是节点的值。
 * 优点：提高了空间效率
 *
 * 思路三：
 * 1. 设 A 的长度为 a + c，B 的长度为 b + c，其中 c 为尾部公共部分长度，可知 a + c + b = b + c + a；
 * 2. 当访问链表 A 的指针访问到链表尾部时，令它从链表 B 的头部重新开始访问链表 B；
 * 3. 同样地，当访问链表 B 的指针访问到链表尾部时，令它从链表 A 的头部重新开始访问链表 A；
 * 4. 这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;

        }
    }

    // 思路二
    public ListNode firstCommonNodesInLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        int len1 = getLength(head1);
        int len2 = getLength(head2);

        // 先默认 len1 长，如果的确是 len2 长的话，则更新差值 d
        int d = len1 - len2;
        ListNode longList = head1;
        ListNode shortList = head2;
        if (len2 > len1) {
            longList = head2;
            shortList = head1;
            d = len2 - len1;
        }

        // 长链表先走 d 步
        for (int i = 0; i < d; i++) {
            longList = longList.next;
        }

        // 两个链表从同一个位置一起走，直到返回相等的节点
        while (longList != null && shortList != null && longList != shortList) {
            longList = longList.next;
            shortList = shortList.next;
        }
        return longList;
    }

    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int len = 0;
        ListNode node = head;
        while (node != null) {
            len++;
            node = node.next;
        }
        return len;
    }

    // 思路三
    public ListNode findFirstCommonNode2(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        ListNode l1 = head1, l2 = head2;
        while (l1 != l2) {
            l1 = (l1 == null) ? head2 : l1.next;
            l2 = (l2 == null) ? head1 : l2.next;
        }
        return l1;
    }
}
