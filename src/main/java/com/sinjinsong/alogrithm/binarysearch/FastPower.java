package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class FastPower {
    /**
     * 求k的n次方，结果对mod取余（避免溢出）
     * 设置当前算子（curr）初值为k。
     * 将N转为二进制形式，从最后一位开始从后向前遍历。
     * 如果当前位为1，那么result * curr，否则result不变。
     * curr乘其自身（指数翻倍），N右移。
     * 直至N为0。
     * 时间复杂度为O(log n)
     *
     * @param base
     * @param exp
     * @return
     */
    public static double power(double base, int exp) {
        if (Double.compare(base, 0) == 0 && exp < 0) {
            throw new IllegalArgumentException("0的负数次方不存在");
        }
        if (exp == 0) {
            // 这里假定0的0次方为1
            return 1;
        }
        if (exp == 1) {
            return base;
        }
        double result = 1;
        double curr = 0;
        if (exp > 0) {
            curr = base;
        } else {
            curr = 1 / base;
        }
        exp = exp > 0 ? exp : -exp;
        while (exp != 0) {
            // 如果n的最低位为1
            if ((exp & 1) == 1) {
                result *= curr;
            }
            exp >>= 1;
            curr *= curr;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(power(10, -3));
    }
}
