package SwordToOfferSolution._48_LongestSubstringWithoutDup;

import java.util.Arrays;
import java.util.HashSet;

/*
 * 最长不含重复字符的子字符串
 *
 * 题目描述：
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 假设字符串中只包含从 a 到 z 的字符。
 *
 * 思路 1：DP
 * https://dyfloveslife.github.io/2019/12/11/offer-longest-SubString-Without-Duplication/
 *
 * 思路 2：使用滑动窗口 + HashSet
 */
public class Solution {

    public int longestSubstringWithoutDup0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int max = 0;
        HashSet<Character> set = new HashSet<>();

        while (right < s.length()) {
            if (!set.contains(s.charAt(right))) {
                set.add(s.charAt(right));
                right++;
                max = Math.max(max, set.size());
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }
        return max;
    }

    public int longestSubstringWithoutDup1(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        int curLen = 0;
        int maxLen = 0;
        int[] position = new int[26];
        Arrays.fill(position, -1);
//        for (int i = 0; i < position.length; i++) {
//            position[i] = -1;
//        }

        for (int curIndex = 0; curIndex < str.length(); curIndex++) {
            int c = str.charAt(curIndex) - 'a';
            int preIndex = position[c];

            if (preIndex == -1 || curIndex - preIndex > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(curLen, maxLen);
                curLen = curIndex - preIndex;
            }
            position[c] = curIndex;
        }
        maxLen = Math.max(curLen, maxLen);
        return maxLen;
    }

    public int longestSubstringWithoutDup2(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int preLength = 0;
        int curLength = 0;
        int maxLength = 0;
        int[] position = new int[26];
        for (int i = 0; i < position.length; i++) {
            position[i] = -1;
        }
        for (int i = 0; i < str.length(); i++) {
            int prevIndex = position[str.charAt(i) - 'a'];
            // 第i个字符之前没有出现过或者出现过但是距离大于f(i-1)
            if (prevIndex < 0 || i - prevIndex > preLength) {
                curLength = preLength + 1;
            } else {
                curLength = i - prevIndex;
            }
            position[str.charAt(i) - 'a'] = i;
            if (curLength > maxLength) {
                maxLength = curLength;
            }
            preLength = curLength;
        }
        return maxLength;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "arabcacfr";

        System.out.println(solution.longestSubstringWithoutDup0(str));
        System.out.println(solution.longestSubstringWithoutDup1(str));
        System.out.println(solution.longestSubstringWithoutDup2(str));
    }
}
