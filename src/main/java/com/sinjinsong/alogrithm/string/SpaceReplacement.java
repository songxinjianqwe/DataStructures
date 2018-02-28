package com.sinjinsong.alogrithm.string;

/**
 * @author sinjinsong
 * @date 2018/2/26
 */
public class SpaceReplacement {
    /**
     * 空格替换，将空格替换为%20，其他的不变
     * 双指针，都是从后向前移动，直至old指针指向头或者双指针相交
     * @param sb
     * @return
     */
    public static String replaceSpace(StringBuilder sb) {

        int spaceCount = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                spaceCount++;
            }
        }
        int oldIndex = sb.length() - 1;
        sb.setLength(sb.length() + spaceCount * 2);
        for (int i = oldIndex + spaceCount * 2; i >= oldIndex && oldIndex >= 0; oldIndex--) {
            if (sb.charAt(oldIndex) == ' ') {
                sb.setCharAt(i--, '0');
                sb.setCharAt(i--, '2');
                sb.setCharAt(i--, '%');
            } else {
                sb.setCharAt(i--, sb.charAt(oldIndex));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(replaceSpace(new StringBuilder("123 123123  ")));
    }
}
