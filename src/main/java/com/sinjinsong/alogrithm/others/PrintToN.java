package com.sinjinsong.alogrithm.others;


/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class PrintToN {
    /**
     * 打印从1到最大的n位十进制数
     * 比如n=3，则打印出1,2,3,...999
     * 可以视为000~999的全排列
     *
     * @param n
     */
    public static void printToMaxNDigits(int n) {
        char[] number = new char[n];
        for (int i = 0; i < 10; i++) {
            number[0] = (char) ('0' + i);
            printToMaxOfNDigitsRecursively(number, 0);
        }
    }

    private static void printToMaxOfNDigitsRecursively(char[] number, int index) {
        if (index == number.length) {
            System.out.println(new String(number));
        } else {
            for (int i = 0; i < 10; i++) {
                number[index] = (char) ('0' + i);
                printToMaxOfNDigitsRecursively(number, index + 1);
            }
        }
    }


    public static void main(String[] args) {
        printToMaxNDigits(3);
    }
}
