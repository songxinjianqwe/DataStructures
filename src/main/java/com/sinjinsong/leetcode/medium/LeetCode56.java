package com.sinjinsong.leetcode.medium;

import java.util.*;

public class LeetCode56 {
    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出: [[1,6],[8,10],[15,18]]
     * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
     * 示例 2:
     *
     * 输入: intervals = [[1,4],[4,5]]
     * 输出: [[1,5]]
     * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
     * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
     *
     *  
     *
     * 提示：
     *
     * intervals[i][0] <= intervals[i][1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        List<int[]> input = new ArrayList<>();
        for (int[] interval : intervals) {
            input.add(interval);
        }
        Collections.sort(input, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int[] pre = input.get(0);
        List<int[]> ans = new ArrayList<>();
        for (int i = 1; i < input.size(); i++) {
            if(pre[1] >= input.get(i)[0]) {
                pre = new int[] { Math.min(pre[0], input.get(i)[0]), Math.max(pre[1], input.get(i)[1])};
            } else {
                ans.add(pre);
                pre = input.get(i);
            }
        }
        ans.add(pre);
        int[][] res = new int[ans.size()][2];
        for (int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }
        return res;
    }
}
