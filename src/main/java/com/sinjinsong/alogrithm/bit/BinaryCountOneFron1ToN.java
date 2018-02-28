package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class BinaryCountOneFron1ToN {
    /**
     * 时间复杂度为O(n log n)
     * N * logN
     * @param n
     * @return
     */
    public static int countOne(int n) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += BinaryCountOne.countOne(n);
        }
        return result;
    }
    
    
}
