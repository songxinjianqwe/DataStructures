package com.sinjinsong.alogrithm;

import java.util.Arrays;

public class Permutation {
    public void permute(String str) {
        permute(str.toCharArray(), 0, str.length() - 1);
    }
	
    public void permute(char[] str, int low, int high) {
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

    private void swap(char[] str, int i, int j) {
        char t = str[i];
        str[i] = str[j];
        str[j] = t;
    }

    public static void main(String[] args) {
        Permutation p = new Permutation();
        p.permute("abc");
    }
}
