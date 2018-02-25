package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class FindMinOfCircularOrderedArray {
    /**
     * 令low初值为0，high初值为N-1.。
     * 1）首先判断arr[low]<arr[high]，如果为true，那么说明low~high范围内是一个正常的有序数组，没有循环部分，那么最小值即为arr[0]（唯一的返回位置）
     * 2）mid 为 low和high的中间位置。
     * 如果arr[mid] < arr[low]，那么说明最小值一定在low与mid之间，high = mid ，因为mid一定处于循环部分。比如【7,8,,9,1,2,3,4,5,6】。进入下次循环。
     * 如果arr[mid] > arr[high]，那么说明最小值一定在mid与high之间，low = mid + 1，因为mid一定处于循环部分。比如【4,5,6,7,8,9,1,2,3】。进入下次循环。
     * 3）如果都不满足，那么说明arr[low]=arr[high]=arr[mid]。此时无法使用二分查找，只能遍历数组去查找最小值。
     *
     * @param arr
     * @return
     */
    public static int findMin(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int min = Integer.MAX_VALUE;
        while (true) {
            mid = (low + high) / 2;
            // case 1
            if (arr[low] < arr[high]) {
                return arr[low];
            } else if (arr[mid] < arr[low]) {
                // 最小值可能mid，也可能在mid左边
                high = mid;
            } else if (arr[mid] > arr[high]) {
                // 最小值一定在mid右边
                low = mid + 1;
            } else {
                // arr[low] == arr[mid] == arr[high]
                // 无法进行二分搜索，转为顺序搜索
                for (int i = low; i <= high; i++) {
                    min = Math.min(min, arr[i]);
                }
                return min;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {7, 8, 9, 10, 1, 2};
        System.out.println(findMin(arr));
    }
}
