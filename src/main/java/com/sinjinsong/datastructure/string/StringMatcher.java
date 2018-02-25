package com.sinjinsong.datastructure.string;

/**
 * @author sinjinsong
 * @date 2018/2/16
 */
public class StringMatcher {
    public static int simpleMatch(String s, String t) {
        char[] value = s.toCharArray();
        char[] chars = t.toCharArray();
        int i = 0, j = 0;
        while (i < value.length && j < chars.length) {
            if (value[i] == chars[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == chars.length) {
            return i - chars.length;
        } else {
            return -1;
        }
    }

    public static int headTailMatch(String s, String t) {
        char[] value = s.toCharArray();
        char[] chars = t.toCharArray();
        int i = 0, j = 0, k = 0;
        char startChar = chars[0];
        char endChar = chars[chars.length - 1];
        while (i < value.length - chars.length + 1) {
            // i 的值最多为主串的长度-子串的长度，否则就不可能匹配了
            if (value[i] != startChar) {
                i++;
                //首字符相等时
            } else if (value[i + chars.length - 1] != endChar) {
                i++;
                //如果首字符不等或者首字符相等但尾字符不等，i都会++
                //else里面就是首尾字符都相等的情况
            } else {
                j = 1;
                k = 1;
                //从第二个字符开始比较，j是子串索引，k指示了主串内部子串的索引，i指示了主串中子串首字符索引
                while (j < chars.length - 1 && value[i + k] == chars[j]) {
                    k++;
                    j++;
                }
                //退出时要么匹配完毕（比较至倒数第二个字符即可），要么有不同的字符
                if (j == chars.length - 1) {
                    return i;
                } else {
                    i++;
                }
            }
        }
        return -1;
    }


    public static int KMPMatch(String s, String t) {
        char[] value = s.toCharArray();
        char[] chars = t.toCharArray();
        int i = 0, j = 0;
        int[] next = getNext(t);
        while (i < value.length && j < chars.length) {
            if (j == -1 || value[i] == chars[j]) {
                //next数组中存在-1，为了避免越界，增加j == -1这个条件
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == chars.length) {
            return i - chars.length;
        } else {
            return -1;
        }
    }
    
    private static int[] getNext(String str) {
        int[] next = new int[str.length()];
        int i = -1, j = 0;
        char[] chars = str.toCharArray();
        next[0] = -1;
        while (j < chars.length - 1) {
            if (i == -1 || chars[i] == chars[j]) { //相当于chars[next[j]] == chars[j]
                i++;
                j++;
                next[j] = i;
                //通过j指向的字符得到j+1指向的字符的next值 
                //相当于next[j+1] = next[j]+1
            } else {
                i = next[i];
            }
        }
        //j 是 指向当前字符，每次增1或不变，用来控制何时结束
        //i 会不断变化，向前进行跳转
        //也可以视j为主串指针，i为子串指针（虽然主串和子串都是子串）
        return next;
    }
}
