package com.sinjinsong.alogrithm.string;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class ATOI {
    
    /**
     * 比如"12345" 转为int类型的12345
     * 注意要处理:
     * 1）空串
     * 2）只有+和-号
     * 3）第一个位置不是数字或+或-，其他位置不是0~9的字符
     * 4）溢出
     *
     * @param s
     * @return
     */
    public static int atoi(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        long result = 0;
        int flag = 1;
        int i = 0, len = s.length();
        if (len == 0) {
            throw new NumberFormatException(s);
        }
        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                flag = -1;
            } else if (firstChar != '+') {
                throw new NumberFormatException(s);
            }
            if (len == 1) {
                // Cannot have lone "+" or "-"
                throw new NumberFormatException(s);
            }
            i++;
        }
        for (; i < len; i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                result = result * 10 + flag * (s.charAt(i) - '0');
                // 如果是正的，并且大于MAX_VALUE 
                // || 如果是负的。并且小于MIN_VALUE
                // 都视为溢出
                if ((flag == -1 && result < Integer.MIN_VALUE) ||
                        (flag == 1 && result > Integer.MAX_VALUE)) {
                    throw new NumberFormatException("overflow");
                }
            } else {
                throw new NumberFormatException(s);
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        int i = atoi("");
        System.out.println(i);
    }
}
