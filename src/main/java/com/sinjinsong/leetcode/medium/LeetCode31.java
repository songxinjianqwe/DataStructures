package com.sinjinsong.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class LeetCode31 {
    public void nextPermutation(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int index = nums.length - 1;
        List<Integer> ans = new ArrayList<>();
        while(index >= 0) {
            if(nums[index] < max) {

            }
        }
    }
}
