package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class BinaryCountOne {
    /**
     * 计算n的二进制中1的个数
     * 是将一个数字每次左移，与n取与判断每位是否是1
     * 时间复杂度为O(log n)
     * @param n
     * @return
     */
    public static int countOne(int n) {
        int count = 0;
        int onePosition = 1;
        while (onePosition != 0) {
            if ((n & onePosition) != 0) {
                count++;
            }
            onePosition <<= 1;
        }
        return count;
    }

    /**
     * 如果是n算术右移的话，如果是负数，那么左侧会不断补1，这样计算结果就错了
     * 这里n是逻辑右移，始终补0，不会补符号位
     * @param n
     * @return
     */
    public static int countOne2(int n) {
        int count = 0;
        while(n != 0) {
            if((n & 1) == 1) {
                count++;
            }
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countOne(-1));
        System.out.println(countOne2(-1));
        
    }
}
