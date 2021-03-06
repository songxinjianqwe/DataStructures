package com.sinjinsong.leetcode.easy;

import java.util.Arrays;

public class LeetCode1879 {
    /**
     * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
     *
     * 初始化 A 和 B 的元素数量分别为 m 和 n。
     *
     * 示例:
     *
     * 输入:
     * A = [1,2,3,0,0,0], m = 3
     * B = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     * 说明:
     *
     * A.length == n + m
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int index = m + n -1;
        int aCursor = m - 1;
        int bCursor = n - 1;

        while(aCursor >= 0 && bCursor >= 0) {
            if(A[aCursor] >= B[bCursor]){
                A[index--] = A[aCursor--];
            } else {
                A[index--] = B[bCursor--];
            }
        }
        while(bCursor >= 0) {
            A[index--] = B[bCursor--];
        }
    }

    public static void main(String[] args) {
        int[] A = new int[] {1,2,3,0,0,0};
        int[] B = new int[] {2,5,6};
        new LeetCode1879().merge(A, 3, B, 3);
        System.out.println(Arrays.toString(A));
    }
}
