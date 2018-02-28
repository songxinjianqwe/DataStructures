package com.sinjinsong.alogrithm.others;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class Prime {
    /**
     * 求3~n-1之间的所有素数
     *
     * @param n
     */
    public static List<Integer> prime(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 3; i < n; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> prime = prime(10);
        System.out.println(prime);
    }
} 
