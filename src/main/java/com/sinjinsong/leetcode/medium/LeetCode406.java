package com.sinjinsong.leetcode.medium;

import java.util.*;

public class LeetCode406 {
    public int[][] reconstructQueue(int[][] people) {
        List<int[]> ordered = new ArrayList<>();
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 先按身高降序排序，再按前面的人数升序排序，这样最前面的的就是身高最高的，并且前面也没有人的
                return o1[0] != o2[0] ? o2[0] - o1[0] : o1[1] - o2[1];
            }
        });
        for (int[] person : people) {
            // 插队
            // 先放高个子的话，矮个子前面的人数就是矮个子所处的位置
            // 因为放矮个子的时候，队列中的都是比他高
            // 假如他插入到i位置，那么i前面的人都要比他高，这样就符合了题目中的对[1]值的定义
            ordered.add(person[1], person);
        }
        return ordered.toArray(new int[][]{});
    }
}
