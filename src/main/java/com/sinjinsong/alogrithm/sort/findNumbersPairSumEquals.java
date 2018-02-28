package com.sinjinsong.alogrithm.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class findNumbersPairSumEquals {
    /**
     * 重点是数组有序
     * low指针指向数组开头，high指针指向数组结尾
     * 相加结果大于target，则high--
     * 小于target，则low++
     * 
     * 时间复杂度为O(n)
     * @param arr
     * @param sum
     * @return
     */
    public static List<Integer> findNumbersPairSumEquals(int[] arr, int sum) {
        if(arr == null || arr.length == 0 ) {
            return Collections.emptyList();
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            if (arr[low] + arr[high] == sum) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[low]);
                list.add(arr[high]);
                return list;
            } else if (arr[low] + arr[high] > sum) {
                high--;
            } else if (arr[low] + arr[high] < sum) {
                low++;
            }
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 11, 15};
        System.out.println(findNumbersPairSumEquals(arr, 15));
    }
}
