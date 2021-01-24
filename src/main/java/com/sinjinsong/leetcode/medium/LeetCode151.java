package com.sinjinsong.leetcode.medium;

public class LeetCode151 {
    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * <p>
     * 说明：
     * <p>
     * 无空格字符构成一个 单词 。
     * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     *  
     * <p>
     * 示例 1：
     * <p>
     * 输入："the sky is blue"
     * 输出："blue is sky the"
     * 示例 2：
     * <p>
     * 输入："  hello world!  "
     * 输出："world! hello"
     * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * <p>
     * 输入："a good   example"
     * 输出："example good a"
     * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * 示例 4：
     * <p>
     * 输入：s = "  Bob    Loves  Alice   "
     * 输出："Alice Loves Bob"
     * 示例 5：
     * <p>
     * 输入：s = "Alice does not even like bob"
     * 输出："bob like even not does Alice"
     *  
     * <p>
     * 提示：
     * <p>
     * 1 <= s.length <= 104
     * s 包含英文大小写字母、数字和空格 ' '
     * s 中 至少存在一个 单词
     *  
     * <p>
     * 进阶：
     * <p>
     * 请尝试使用 O(1) 额外空间复杂度的原地解法。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        char[] chars = s.trim().toCharArray();
        reverse(chars, 0, chars.length - 1);
        Character lastChar = null;
        int len = chars.length;
        for (int i = 1; i < len; ) {
            if (chars[i] == ' ' && chars[i - 1] == ' ') {
                for (int j = i + 1; j < chars.length; j++) {
                    chars[j - 1] = chars[j];
                }
                // 有可能连着三个空格，此时不应该推进指针
                len--;
            } else {
                i++;
            }
        }

        int start = 0;
        for (int i = 0; i < len; i++) {
            if (chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            } else if (i == len - 1) {
                reverse(chars, start, i);
            }
        }
        return new String(chars, 0, len);
    }

    private void reverse(char[] chars, int low, int high) {
        for (int i = 0; i <= (high - low) / 2; i++) {
            char temp = chars[i + low];
            chars[i + low] = chars[high - i];
            chars[high - i] = temp;
        }
    }

    public static void main(String[] args) {
        String s = new LeetCode151().reverseWords("a good   example");
        System.out.println(s);
    }
}
