package com.sinjinsong.alogrithm.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sinjinsong
 * @date 2018/3/1
 */
public class SubSet {
    /**
     * 列出一个集合中的所有非空子集
     * 含n个元素的集合有2^n - 1个子集，因此算法的时间复杂度为O(2^n)
     * @param str
     * @return
     */
    public static List<String> listAllSubSets(String str) {
        List<String> result = new ArrayList<>();
        if (str.length() == 0) {
            return result;
        }
        char[] chars = str.toCharArray();
        int bitmap = 0;
        // 原字符串“aefea” bitmap二进制为00…00110001(共26位)
        for (int i = 0; i < chars.length; i++) {
            // 如果a存在，那么bitmap倒数第一位置为1
            // 如果z存在，那么bitmap倒数第26位置为1
            bitmap |= 1 << (chars[i] - 'a');
        }
        System.out.println(Integer.toBinaryString(bitmap));
        
        int key;
        int charIndex;
        // 每次打印一个子集
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < Math.pow(2, str.length()); i++) {
            key = i & bitmap;
            charIndex = 0;
            while(key != 0) {
                // key就是要打印的字符串对应的bit们
                // while循环逐个找出要打印的字符
                if((key & 1) == 1) {
                    sb.append((char)('a' + charIndex));
                }
                key >>= 1;
                charIndex++;
            }
            result.add(sb.toString());
            sb.delete(0,sb.length());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(listAllSubSets("abc"));
    }
}
