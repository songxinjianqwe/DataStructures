package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/25
 */
public class FindOnlyAppearOddTimes {
    /**
     * e与0异或，得到e
     * e与1异或，得到e取反值
     * e与e异或，得到全0
     * 
     * 因为异或满足交换律和结合律，可以视为出现偶数次的元素先进行了异或，得到了0，那么结果就是e(值为0)^0^出现奇数次的元素
     * @param arr
     * @return
     */
    public static int get(int [] arr) {
        int e = 0;
        for (int i = 0; i < arr.length; i++) {
            e ^= arr[i];
        }
        return e;
    }

    public static void main(String[] args) {
        System.out.println(get(new int[]{3,2,4,1,1,2,3}));
    }
}
