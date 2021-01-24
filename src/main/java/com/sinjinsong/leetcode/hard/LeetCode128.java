package com.sinjinsong.leetcode.hard;

import java.util.HashSet;
import java.util.Set;

public class LeetCode128 {
    public int longestConsecutive(int[] nums) {
        if(nums.length <= 1) {
            return nums.length;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 1;
        for (int num : nums) {
            if(set.contains(num - 1)) {
                continue;
            }
            if(set.contains(num + 1)) {
                int dist = 1;
                int val = num + 1;
                while(set.contains(val++)) {
                    dist++;
                }
                ans = Math.max(ans, dist);
            }
        }
        return ans;
    }
}
