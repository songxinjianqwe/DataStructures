package com.sinjinsong.datastructure;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class ArrayUtils {
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    } 
}
