package com.sinjinsong.alogrithm.string;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class REMatch {
    /**
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。
     * 模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
     * 在本题中，匹配是指字符串的所有字符匹配整个模式。
     * 例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     * <p>
     * 当模式中的第二个字符不是“*”时：
     * 1、如果字符串第一个字符和模式中的第一个字符相匹配，那么字符串和模式都后移一个字符，然后匹配剩余的。
     * 2、如果 字符串第一个字符和模式中的第一个字符相不匹配，直接返回false。
     * <p>
     * 而当模式中的第二个字符是“*”时：
     * 如果字符串第一个字符跟模式第一个字符不匹配，则模式后移2个字符，继续匹配。如果字符串第一个字符跟模式第一个字符匹配，可以有3种匹配方式：
     * 1、模式后移2字符，相当于x*被忽略；
     * 2、字符串后移1字符，模式后移2字符；
     * 3、字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；
     *
     * @param str
     * @param pattern
     * @return
     */
    public static boolean match(char[] str, char[] pattern) {
        if (str == null || pattern == null ) {
            return false;
        }
        return matchCore(str, pattern, 0, 0);
    }

    private static boolean matchCore(char[] str, char[] pattern, int strIndex, int patternIndex) {
        if (strIndex == str.length && patternIndex == pattern.length) {
            return true;
        }
        // pattern先结束，则不匹配
        if (strIndex != str.length && patternIndex == pattern.length) {
            return false;
        }
        // 此时pattern一定未结束，str可能结束
        
        // 下一个pattern是*吗？如果还有下一个的话
        if (patternIndex + 1 != pattern.length && pattern[patternIndex + 1] == '*') {
            // 要求当前字符相同或者pattern是.
            if (strIndex != str.length &&
                    (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
                // 模式后移2字符，相当于x*被忽略
                return matchCore(str, pattern, strIndex, patternIndex + 2) ||
                        // 字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位
                        matchCore(str, pattern, strIndex + 1, patternIndex);
                // 还有一种情况：字符串后移1字符，模式后移2字符；可以先执行一次1，再执行一次2，就实现这种情况
            } else {
                // 否则
                return matchCore(str, pattern, strIndex, patternIndex + 2);
            }
        }
        
        // 下一个pattern不是*/没有下一个了，比较当前字符
        if (strIndex != str.length &&  (pattern[patternIndex] == str[strIndex] || pattern[patternIndex] == '.')) {
            return matchCore(str, pattern, strIndex + 1, patternIndex + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(match("a".toCharArray(),".".toCharArray()));
    }
}
