package com.sinjinsong.leetcode.easy;

public class LeetCode121 {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) {
            return 0;
        }
        int profit = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] < max) {
                profit = Math.max(profit, max - prices[i]);
            } else if (prices[i] > max) {
                max = prices[i];
            }
        }
        return profit;
    }



}
