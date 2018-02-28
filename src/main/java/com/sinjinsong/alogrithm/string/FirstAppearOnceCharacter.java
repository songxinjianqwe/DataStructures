package com.sinjinsong.alogrithm.string;

import java.util.Arrays;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class FirstAppearOnceCharacter {
    
    
    /**
     * 哈希表
     * 因为哈希表大小与str的长度无关
     * 所以空间复杂度为O(1)
     * 时间复杂度为O(n)
     * @param str
     * @return
     */
    public static int appearOnceIndex(String str) {
        int[] map = new int[256];
        for (int i = 0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }
        System.out.println(Arrays.toString(map));
        for (int i = 0; i < str.length(); i++) {
            if(map[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(appearOnceIndex("google"));
    }
}
