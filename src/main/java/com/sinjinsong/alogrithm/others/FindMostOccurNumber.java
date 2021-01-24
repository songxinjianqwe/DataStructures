package com.sinjinsong.alogrithm.others;

public class FindMostOccurNumber {
    // 数组中出现频率最高的数字，多个的话取最大的
    // 数组中的数字大小在1~N之间（含头含尾）
//    public int solve(int[] nums) {
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] - 1 == i) {
//                nums[i] = -1;
//            } else {
//                // 数组里的数据是1~N，先都减去1，不然索引会越界，最后返回结果的时候再加1
//                nums[i]--;
//            }
//        }
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] > 0) {
//                // 自己没有被计算过
//                countMe(nums, i);
//            }
//        }
//        int ans = 0;
//        int max = 0;
//        for (int i = nums.length - 1; i >= 0; i--) {
//            if (max < -nums[i]) {
//                max = -nums[i];
//                ans = i;
//            }
//        }
//        return ans + 1;
//    }
//
//    private void countMe(int[] nums, int index) {
//        if (nums[nums[index]] > 0) {
//            // 想放的位置还没被计算过，就递归计算想放的位置
//            countMe(nums, nums[index]);
//        }
//        nums[nums[index]]--;
//        // 自己已经被算过了，置为0，外层循环就不会重复算了
//        nums[index] = 0;
//    }

    public int solve(int[] nums) {
        int ans = 0;
        int max = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            nums[i]--;
        }
        for (int i = 0; i < n; i++) {
            // i 这个数字的出现次数索引
            int countIndex = nums[i] % n;
            nums[countIndex] += n;
            if (nums[countIndex] > max) {
                max = nums[countIndex];
                ans = countIndex;
            }
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        {
            int[] nums = {2, 3, 1, 3, 5};
            int ans = new FindMostOccurNumber().solve(nums);
            System.out.println(ans);
        }
        {
            int[] nums = {2, 3, 1, 3, 4, 5, 4, 6};
            int ans = new FindMostOccurNumber().solve(nums);
            System.out.println(ans);
        }
        {
            int[] nums = {1, 2, 3, 4};
            int ans = new FindMostOccurNumber().solve(nums);
            System.out.println(ans);
        }
    }
}
