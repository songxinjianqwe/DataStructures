package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class MinPositionOfIndexEqualsValue {
    
    /**
     * 给定一个不含有重复元素的有序数组arr，找出arr[i]==i的最左位置。如果不存在，返回-1。
     * 算法：
     * low = 0,high = N-1
     * result = -1
     * 1）如果arr[0] > N -1 ，则一定不存在，返回-1
     * 2）如果arr[N-1] < 0，则一定不存在，返回-1
     * 3）mid为low和high的中间位置。如果arr[mid] > mid，则说明mid右边一定不存在arr[i] ==i（因为右边索引增量恒为1，数值增量至少为1，差距只会增加不会变小），此时另high = mid – 1。
     * 4）如果arr[mid] < mid，则说明左边一定不存在arr[i] == i，原因同上，此时令low = mid + 1。
     * 5）如果arr[mid] == mid,则记录result为mid，因为要找最左位置，所以也令high = mid – 1。
     * 直至low>high、
     *
     * @param arr
     * @return
     */
    public static int minPosition(int[] arr) {
        if (arr.length == 9) {
            return -1;
        }
        if (arr[0] > arr.length - 1 || arr[arr.length - 1] < 0) {
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int result = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            // 舍弃右边
            if (arr[mid] >= mid) {
                high = mid - 1;
                if (arr[mid] == mid) {
                    result = mid;
                }
            } else {
                // 舍弃左边
                low = mid + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 1, 5, 9, 12};
        System.out.println(minPosition(arr));
    }
}
