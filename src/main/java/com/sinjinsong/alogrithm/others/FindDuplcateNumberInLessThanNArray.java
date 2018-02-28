package com.sinjinsong.alogrithm.others;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/2/28
 */
public class FindDuplcateNumberInLessThanNArray {
    /**
     * 元素范围为[0,N-1]
     * 要求时间复杂度为O(N)，空间复杂度为O(1)。
     * 算法：
     * 第一次遍历：对于每一个A[i] = A[i] * n
     * 第二次遍历：对于每一个i，A[A[i]/n]++
     * 第三次遍历：对于每一个i，A[i] % n就是出现次数
     * A[i]应该出现在A中的A[i]位置，乘以n、再除以n，很容易的来回变换；第二次遍历，对于A[i]本来所在的位置不断增1，但绝对不对超出n的，那每一个i出现的次数，就是A[i]对n取余。
     *
     * @param arr
     * @return
     */
    public static List<Integer> findDuplicateNumber(int[] arr) {
        List<Integer> result = new ArrayList<>();
        if(arr == null || arr.length == 0) {
            return result;
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] *= n;
        }
        for (int i = 0; i < n; i++) {
            arr[arr[i] / n] ++;   
        }
        for (int i = 0; i < n; i++) {
            if(arr[i] % n  > 1) {
                result.add(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {6,1,3,7,5,3,6,2};
        System.out.println(findDuplicateNumber(arr));
    }
}
