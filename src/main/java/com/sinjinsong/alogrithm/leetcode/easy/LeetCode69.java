package com.sinjinsong.alogrithm.leetcode.easy;

public class LeetCode69 {
    /**
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sqrtx
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        int low = 0;
        int high = x;
        int mid = 0;
        while (low < high) {
            mid = (low + high) / 2;
            // 避免移除
            if (mid == x / mid) {
                return mid;
            }
            if (mid > x / mid) {
                high = mid;
            } else {
                low = mid;
            }
            // 快速结束，x在mid*mid和(mid+1)*(mid+1)之间
            if (mid < x / mid && (mid + 1) > x / (mid + 1)) {
                return mid;
            }
        }
        return mid;
    }
}