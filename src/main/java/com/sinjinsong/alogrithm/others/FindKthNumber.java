package com.sinjinsong.alogrithm.others;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class FindKthNumber {
    /**
     * 数字序列0123456789101112....
     * 求第k位对应的数字
     * <p>
     * 前10位是0~9的一位数
     * 向后180位是10~99的两位数
     * 向后2700位是100~999的三位数
     *
     * @param k
     * @return
     */
    public static int findKthNumber(int k) {
        if (k < 0) {
            return -1;
        }
        // 1表示一位数，2表示二位数
        // baseNumberCount表示base位数共有多少个数字（不包含base一下位数的数字）

        // baseNumberCount*base表示base位数 共有多少位
        int base = 1;
        while (true) {
            int baseNumberCount = countBase(base);
            if (k < baseNumberCount * base) {
                return findKthNumber(k, base);
            }
            k -= baseNumberCount * base;
            base++;
        }
    }

    /**
     * base位数共有多少个数字
     *
     * @param base
     * @return
     */
    private static int countBase(int base) {
        if (base == 1) {
            return 10;
        }
        return 9 * (int) Math.pow(10, base - 1);
    }

    /**
     * 求在base位数中的第k位
     * 比如三位数中的第38位：100101102103104105106107108109110111112113114... -> 2
     * @param k
     * @param base
     * @return
     */
    private static int findKthNumber(int k, int base) {
        int number = baseBeginNumber(base) + k / base;
        int kFromRight = base - k % base;
        for (int i = 1; i < kFromRight; i++) {
            number /= 10;
        }
        return number % 10;
    }

    private static int baseBeginNumber(int base) {
        if (base == 1) {
            return 0;
        }
        return (int) Math.pow(10, base - 1);
    }
}
