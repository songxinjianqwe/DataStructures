package com.sinjinsong.leetcode.easy;

public class LeetCode371 {
    /**
     * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
     *
     * 示例 1:
     *
     * 输入: a = 1, b = 2
     * 输出: 3
     * 示例 2:
     *
     * 输入: a = -2, b = 3
     * 输出: 1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        // 01 + 01
        // 01 ^ 01 = 00 无进位的相加
        // (01 & 01) << 1 = 10 进位
        // 进位如果为0的情况下，和就是无进位的相加
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
