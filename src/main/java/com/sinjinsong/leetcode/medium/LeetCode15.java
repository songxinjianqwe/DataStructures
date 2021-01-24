package com.sinjinsong.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode15 {
    /**
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [-1,0,1,2,-1,-4]
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 示例 2：
     *
     * 输入：nums = []
     * 输出：[]
     * 示例 3：
     *
     * 输入：nums = [0]
     * 输出：[]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> twoSumAns = twoSum(nums, -nums[i], i);
            for (List<Integer> tempAns : twoSumAns) {
                tempAns.add(nums[i]);
                if(!ans.contains(tempAns)) {
                    ans.add(tempAns);
                }
            }
        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] nums, int expected, int excludedIndex) {
        List<List<Integer>> ans = new ArrayList<>();
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            if (low == excludedIndex) {
                low++;
                break;
            } else if (high == excludedIndex) {
                high--;
                break;
            }
            int current = nums[low] + nums[high];
            if (current == expected) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[low]);
                list.add(nums[high]);
                ans.add(list);
                high--;
                low++;
            } else if (current > expected) {
                high--;
            } else {
                low++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new LeetCode15().threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
