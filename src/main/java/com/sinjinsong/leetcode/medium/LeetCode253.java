package com.sinjinsong.leetcode.medium;

import java.util.*;

public class LeetCode253 {

    /**
     * 给你一个会议时间安排的数组 intervals ，每个会议时间都会包括开始和结束的时间 intervals[i] = [starti, endi] ，为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：intervals = [[0,30],[5,10],[15,20]]
     * 输出：2
     * 示例 2：
     *
     * 输入：intervals = [[7,10],[2,4]]
     * 输出：1
     *  
     *
     * 提示：
     *
     * 1 <= intervals.length <= 104
     * 0 <= starti < endi <= 106
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/meeting-rooms-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        Queue<Integer> q = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if(q.isEmpty()) {
                q.add(interval[1]);
            } else {
                // 当前会议开始时间比当前所有会议室中最晚结束所有会议的会议室的的最晚结束时间还要大，说明是可以复用这个会议室的（排到后面就可以了）
                if(interval[0] >= q.peek()) {
                    // 复用
                    q.poll();
                    q.add(interval[1]);
                } else {
                    q.add(interval[1]);
                }
            }
        }
        return q.size();
    }

    public static void main(String[] args) {
        int[][] interval =  {{0,30},{5,10},{15,20}};
        int i = new LeetCode253().minMeetingRooms(interval);
    }
}
