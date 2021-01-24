package com.sinjinsong.leetcode.medium;

public class LeetCode215 {
    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     *
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        int index = partition(nums, low, high);
        while(index != nums.length - k) {
            if(index > nums.length - k) {
                index = partition(nums, low, index - 1);
            } else {
                index = partition(nums, index + 1, high);
            }
        }
        return nums[index];
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while(low < high) {
            while(low < high && nums[high] >= pivot) {
                high--;
            };
            nums[low] = nums[high];
            while(low < high && nums[low] < pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }


    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,4,5,5,6};
        int kthLargest = new LeetCode215().findKthLargest(arr, 4);
    }
}
