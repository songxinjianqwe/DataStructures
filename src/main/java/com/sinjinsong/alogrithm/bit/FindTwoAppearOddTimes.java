package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class FindTwoAppearOddTimes {

    public static int[] get(int[] arr) {
        int e = 0;
        for (int i = 0; i < arr.length; i++) {
            e ^= arr[i];
        }
        // 此时 e = a ^ b ;
        // 因为a和b不同，那么至少有一位不同，不同位异或，得到的是1，所以e中肯定有一位是1，假设第k位为1
        int k = 1;
        while (((e >> k) & 1) != 1) {
            k++;
        }
        int e1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (((arr[i] >> k) & 1) == 1) {
                e1 ^= arr[i];
            }
        }
        // e1 等于a或b
        // e2 等于b或a
        int e2 = e ^ e1;
        int[] result = {Math.min(e1, e2), Math.max(e1, e2)};
        return result;
    }
}
