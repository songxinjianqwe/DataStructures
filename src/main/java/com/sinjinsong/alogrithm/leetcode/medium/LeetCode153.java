package com.sinjinsong.alogrithm.leetcode.medium;

public class LeetCode153 {
    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。
     *
     * 请找出其中最小的元素。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [3,4,5,1,2]
     * 输出：1
     * 示例 2：
     *
     * 输入：nums = [4,5,6,7,0,1,2]
     * 输出：0
     * 示例 3：
     *
     * 输入：nums = [1]
     * 输出：1
     *  
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while(low < high) {
            int mid = (low + high) / 2;
            if(nums[mid] < nums[high]) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        new LeetCode153().findMin(new int[]{3,4,5,1,2});
    }
}
