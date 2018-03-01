package com.sinjinsong.alogrithm.others;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class SecondMaxNumber {
    public static int secondMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int sec = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max) {
                sec = max;
                max = arr[i];
            }else{
                sec = arr[i];
            }
        }
        return sec;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,3,32,13,21,312,222};
        System.out.println(secondMax(arr));
        
    }
}
