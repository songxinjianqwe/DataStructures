package com.sinjinsong.alogrithm.sort;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class InversePair {

    /**
     * 时间复杂度为O(n^2)
     * 空间复杂度为O(1)
     * @param arr
     * @return
     */
    public static int countInversePair(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] > arr[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    
    public static int countInversePairByMergeSort(int[] arr) {
        int count = 0;
        return count;
    }
    
    public static void main(String[] args) {
        int[] arr = {7,5,6,4};
        System.out.println(countInversePair(arr));
    }
    
    
}
