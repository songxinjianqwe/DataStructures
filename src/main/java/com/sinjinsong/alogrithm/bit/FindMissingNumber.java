package com.sinjinsong.alogrithm.bit;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class FindMissingNumber {
    /**
     * 一个连续的数字数组[1,N]，缺失了一个数，顺序也被打乱了
     * 1）哈希表
     * 2）排序。比较相邻数字是否连续 时间复杂度为O(nlog n)
     * 3）先求1~N的和，再减去数组的和 溢出！ 时间复杂度为O(n)
     * 4）1~N取异或，结果再与数组每个值异或，结果即为缺失的数 不会溢出 
     * @param arr
     * @return
     */
    public static int findMissing(int[] arr, int n) {
        if (arr == null || arr.length == 0 || n <= 0) {
            throw new IllegalArgumentException();
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        int arraySum = 0;
        for (int i = 0; i < arr.length; i++) {
            arraySum += arr[i];
        }
        return sum - arraySum;
    }
    
    public static int findMissingByXOR(int[] arr,int n){
        if (arr == null || arr.length == 0 || n <= 0) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            result ^= i;
        }
        for (int i = 0; i < arr.length; i++) {
            result ^= arr[i];
        }
        return result;
    }


    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 6, 1};
        System.out.println(findMissing(arr, 6));
        System.out.println(findMissingByXOR(arr, 6));
        
    }
}
