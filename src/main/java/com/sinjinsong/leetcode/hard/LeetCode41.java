package com.sinjinsong.leetcode.hard;

public class LeetCode41 {
    /**
     * 一种原地哈希的做法
     * 类似于「找到数值大小在1~N之间的数组里出现最多次数的数字」（将nums[i]的数字的出现次数放到nums[i] % n 这个位置上，++次数就是+N，模N之后拿到原始数字）
     * 当前是把nums[i]数字放到nums[i] - 1位置上（如果数字在1~n之间）
     * 如果全部数字都不在1~n之间，那么特定是1；如果全部数字都在1~n之间，那么第一个就是n+1
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        // [3,4,-1,1]
        // 3 4 -1 1 , i = 0
        // -1 4 3 1 , i = 0
        // -1 1 3 4 , i = 1
        // 1 -1 3 4 , i = 1
        // 1 -1 3 4 , i = 2
        // 1 -1 3 4 , i = 3
        // 结束
        // 此时1就在0位置上，1位置应该是2的，但是是-1，所以返回2
        for (int i = 0; i < nums.length; i++) {
            while(nums[i] >= 1 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
