package com.sinjinsong.alogrithm.leetcode.medium;

public class LeetCode33 {
    /**
     * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
     *
     * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2], target = 3
     * 输出：-1
     * 示例 3：
     *
     * 输入：nums = [1], target = 0
     * 输出：-1
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int low, int high) {
        while(low <= high) {
            int mid = (low + high) / 2;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] < nums[high]) {
                // 说明右半边有序
                if(target >= nums[mid] && target <= nums[high]) {
                    // 如果目标在右半边中，则继续在右半边里查
                    low = mid + 1;
                } else {
                    // 否则继续在左半边里查
                    high = mid - 1;
                }
            } else {
                // 说明左半边有序
                if(target >= nums[low] && target <= nums[mid]) {
                    // 如果目标在左半边中，则继续在左半边中查
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
