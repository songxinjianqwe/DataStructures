package com.sinjinsong.alogrithm.others;

import java.util.Arrays;

public class Permutation {
    public static void permute(String str) {
        permute(str.toCharArray(), 0, str.length() - 1);
    }

    /**
     * [1,2,3]
     * permute(0,2):
     *      i = 0: swap(0,0)
     *             permute(1,2): 
     *                  i = 1: swap(1,1)
     *                         permute(2,2):
     *                              print: [1,2,3]
     *                         swap(1,1)
     *                         
     *                  i = 2: swap(1,2) [1,3,2]
     *                         permute(2,2):
     *                              print: [1,3,2]
     *                         swap(1,2) [1,2,3]
     *             swap(0,0)
     *             
     *      i = 1: swap(1,0) [2,1,3]
     *             permute(1,2): 
     *                  i = 1: swap(1,1)
     *                         permute(2,2):
     *                              print: [2,1,3]
     *                         swap(1,1)
     *                         
     *                  i = 2: swap(2,1) [2,3,1]
     *                         permute(2,2):
     *                              print: [2,3,1]
     *                         swap(2.1) [2,1,3]  
     *             swap(1,0) [1,2,3]
     *             
     *      i = 2: swap(2,0) [3,2,1]
     *             permute(1,2):
     *                  i = 1: swap(1,1)
     *                         permute(2,2):
     *                              print: [3,2,1]
     *                         swap(1,1)
     *                         
     *                  i = 2: swap(2,1) [3,1,2]
     *                         permute(2,2):
     *                              print: [3,1,2]
     *                         swap(2.1) [3,2,1]  
     *             swap(2,0) [1,2,3]            
     * 
     * @param str
     * @param low
     * @param high
     */
    public static void permute(char[] str, int low, int high) {
        if (low == high) {
            System.out.println(Arrays.toString(str));
        } else {
            for (int i = low; i <= high; i++) {
                swap(str, i, low);
                permute(str, low + 1, high);
                swap(str, i, low);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

    public static void main(String[] args) {
        permute("abc");
    }
}
