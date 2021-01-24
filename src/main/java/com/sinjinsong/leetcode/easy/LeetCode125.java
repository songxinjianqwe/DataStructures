package com.sinjinsong.leetcode.easy;

public class LeetCode125 {
    /**
     * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
     *
     * 说明：本题中，我们将空字符串定义为有效的回文串。
     *
     * 示例 1:
     *
     * 输入: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     *
     * 输入: "race a car"
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-palindrome
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        if(s.length() == 0) {
            return true;
        }
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while(i < j) {
            char low;
            char high;
            while(i < j && !isValid(chars[i])){
                i++;
            }
            while(i < j && !isValid(chars[j])){
                j--;
            }
            if(chars[i] >='A' && chars[i] <= 'Z') {
                low = (char) (chars[i] - 'A' + 'a');
            } else {
                low = chars[i];
            }
            if(chars[j] >='A' && chars[j] <= 'Z') {
                high = (char) (chars[j] - 'A' + 'a');
            } else {
                high = chars[j];
            }
            if(low != high) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean isValid(char aChar) {
        return aChar >= '0' && aChar <= '9'
                || aChar >= 'a' && aChar <= 'z'
                || aChar >= 'A' && aChar <= 'Z';
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode125().isPalindrome("A man, a plan, a canal: Panama"));
    }

}
