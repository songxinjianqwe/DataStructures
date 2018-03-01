package com.sinjinsong.alogrithm.bit;

import java.util.*;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class FindDuplicateInteger {
    
    /**
     * 
     * @param arr
     * @return
     */
    public static Set<Integer> findDuplicateIntegers(int[] arr) {
        Set<Integer> result = new HashSet<>();
        BitSet bitmap = new BitSet(Integer.MAX_VALUE);
        for (int i = 0; i < arr.length; i++) {
            if(bitmap.get(arr[i])){
                result.add(arr[i]);
            }else{
                bitmap.set(arr[i]);
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Set<Integer> duplicateIntegers = findDuplicateIntegers(new int[]{1, 9, 9, 9, 2147483645,2147483645, 2});
        System.out.println(duplicateIntegers);
    }
}
