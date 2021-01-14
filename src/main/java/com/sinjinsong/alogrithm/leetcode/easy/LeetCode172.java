package com.sinjinsong.alogrithm.leetcode.easy;

public class LeetCode172 {


    /**
     *给定一个整数 n，返回 n! 结果尾数中零的数量。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     * 示例 2:
     *
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * 说明: 你算法的时间复杂度应为 O(log n) 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        // 首先题目的意思是末尾有几个0
        //    比如6! = 【1* 2* 3* 4* 5* 6】
        //    其中只有2*5末尾才有0，所以就可以抛去其他数据 专门看2 5 以及其倍数 毕竟 4 * 25末尾也是0
        //    比如10！ = 【2*4*5*6*8*10】
        //    其中 4能拆成2*2  10能拆成2*5
        //    所以10！ = 【2*（2*2）*5*（2*3）*（2*2*2）*（2*5）】
        //    一个2和一个5配对 就产生一个0 所以10！末尾2个0
        //
        //    转头一想 2肯定比5多 所以只数5的个数就行了
        int ans = 0;
        // 10 -> 2
        // 10 / 5 = 2
        while(n >= 5) {
            n /= 5;
            ans += n;
        }
        return ans;
    }
}
