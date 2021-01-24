package com.sinjinsong.leetcode.medium;

public class LeetCode55 {
    public boolean canJump(int[] nums) {
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            // 从以前位置能跳到的最大距离要比当前位置小，也就是说跳不到当前位置
            if(maxLength < i) {
                return false;
            }
            // 从当前位置以及之前的位置能跳到的最大距离
            // i + nums[i]是指从当前位置能跳到的最大距离
            // [2,3,1,1,4]
            // [0] maxLength = 0 + 2
            // [1] maxLength = MAX(2, 1 + 3) = 4
            // [2] maxLength = MAX(4, 2 + 1) = 4
            // [3] maxLength = MAX(4, 3 + 1) = 4
            // [4] maxLength = MAX(4, 4 + 1) = 5
            maxLength = Math.max(maxLength, i + nums[i]);
        }
        return true;
    }
}
