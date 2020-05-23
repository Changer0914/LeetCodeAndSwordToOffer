package SwordToOfferSolution._55_02_BalancedBinaryTree;

/*
 * 平衡二叉树
 *
 * 题目描述：
 * 输入一棵二叉树的根结点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意结点的左右子树的深度相差不超过 1，那么它就是一棵平衡二叉树。
 *
 * 思路 1：
 * 后序遍历二叉树节点，同时统计当前节点的深度；
 *
 * 思路 2：
 * 1. 由于递归可以访问当前节点三次，所以使用递归；
 * 2. 先判断当前节点的左子树是否平衡，并将高度记录下来，右子树也做相同的操作；
 * 3. 然后当前节点将收集到的信息进行判断，从而判断整棵树是否是平衡二叉树。
 *
 * 思路 3：
 * 与统计二叉树高度类似，只不过在返回之前判断左右子树的高度差即可。
 *
 * 树型 DP
 */
public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 方法一：
    // 在后序遍历到当前节点的时候，该节点的左右子树就已经遍历了
    // 从底往上遍历，每个节点只需要遍历一次
    private boolean isB = true;

    public boolean isBalanced(TreeNode root) {
        getDepth(root);
        return isB;
    }

    private int getDepth(TreeNode root) {
        if (root == null || !isB) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isB = false;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 方法二：双层递归
    // 在求二叉树深度的基础上，求出左右子树的深度，
    // 缺点是一个节点会被重复遍历多次
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftDepth = getTreeDepth(root.left);
        int rightDepth = getTreeDepth(root.right);

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }

    // ※ 思路二
    public static class ReturnData {
        boolean isB;
        int h;

        ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    public static boolean isB(TreeNode head) {
        return process(head).isB;
    }

    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }

        ReturnData leftData = process(head.left);
        if (!leftData.isB) {
            return new ReturnData(false, 0);
        }
        ReturnData rightData = process(head.right);
        if (!rightData.isB) {
            return new ReturnData(false, 0);
        }
        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(leftData.h, rightData.h) + 1);
    }

    // 后序遍历+剪枝
    // 也就是自底向上，由于后序遍历是左右根，因此当遍历完左右子树的时候，就从底至顶返回子树深度，
    // 若判定某子树不是平衡树则“剪枝”，直接向上返回
    // 如果 root 左右子树的深度差大于 2，则返回 -1，表示不是平衡树
    // 如果 root 左右子树的深度差小于等于 1，则当前子树的深度就是左右子树深度的最大值加 1
    public boolean isBalanced3(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = recur(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = recur(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        return Math.abs(leftDepth - rightDepth) < 2 ? Math.max(leftDepth, rightDepth) + 1 : -1;
    }
}
