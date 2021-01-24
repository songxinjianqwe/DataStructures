package com.sinjinsong.leetcode.easy;

public class LeetCode169 {
    public int majorityElement(int[] nums) {
        int mid = (nums.length-1)/2;
        int index = partition(nums, 0, nums.length - 1);
        while(index != mid) {
            if(index > mid) {
                // 说明第(n-1)/2大的数字在左半边
                index = partition(nums, 0, index -1);
            } else {
                index = partition(nums, index + 1, nums.length - 1);
            }
        }
        return nums[index];
    }

    public int partition(int[] nums, int low, int high) {
        if(low < high) {
            int pivot = nums[low];
            while(low < high) {
                while(low < high && pivot <= nums[high]) {
                    high--;
                }
                nums[low] = nums[high];
                while(low < high && pivot > nums[low]) {
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = pivot;
        }
        return low;
    }
}
