package com.sinjinsong.leetcode.easy;

public class Offer21 {
    //    public int[] exchange(int[] nums) {
//        int ansIndex = nums.length - 1;
//        Deque<Integer> q = new ArrayDeque<>();
//        for (int i = nums.length - 1; i >= 0; i--) {
//            if((nums[i] & 1) == 1) {
//                q.addFirst(nums[i]);
//            } else {
//                nums[ansIndex--] = nums[i];
//            }
//        }
//        while(!q.isEmpty()) {
//            nums[ansIndex--] = q.removeFirst();
//        }
//        return nums;
//    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
     *
     *  
     *
     * 示例：
     *
     * 输入：nums = [1,2,3,4]
     * 输出：[1,3,2,4]
     * 注：[3,1,2,4] 也是正确的答案之一。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            while (low < high && nums[low] % 2 == 1) {
                low++;
            }
            while (low < high && nums[high] % 2 == 0) {
                high--;
            }
            if (low < high) {
                int temp = nums[low];
                nums[low] = nums[high];
                nums[high] = temp;
            }
        }
        return nums;
    }
}
