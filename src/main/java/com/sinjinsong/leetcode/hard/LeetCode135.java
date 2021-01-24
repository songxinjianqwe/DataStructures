package com.sinjinsong.leetcode.hard;

import com.sinjinsong.leetcode.medium.LeetCode22;

import java.util.Arrays;

public class LeetCode135 {

    public int candy(int[] ratings) {
        // left[i]是指满足 i 比 i-1 大的时候 + 1 所需要的糖果数量
        // right[i]是指满足 i 比 i+1 大的时候 + 1 所需要的糖果数量
        // 二者中的较大值是满足需求的最少糖果数量
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < ratings.length; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int candy = new LeetCode135().candy(new int[]{1, 0, 2, 3, 2, 4});
        System.out.println(candy);
    }
}
