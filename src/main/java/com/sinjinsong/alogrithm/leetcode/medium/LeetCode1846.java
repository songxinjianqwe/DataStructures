package com.sinjinsong.alogrithm.leetcode.medium;

import java.util.Stack;

public class LeetCode1846 {
    /**
     * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
     *
     * 示例1:
     *
     *  输入：
     * ["SortedStack", "push", "push", "peek", "pop", "peek"]
     * [[], [1], [2], [], [], []]
     *  输出：
     * [null,null,null,1,null,2]
     * 示例2:
     *
     *  输入：
     * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
     * [[], [], [], [1], [], []]
     *  输出：
     * [null,null,null,null,null,true]
     * 说明:
     *
     * 栈中的元素数目在[0, 5000]范围内。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-of-stacks-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class SortedStack {
        private Stack<Integer> data = new Stack<>();
        private Stack<Integer> helper = new Stack<>();


        public SortedStack() {

        }

        public void push(int val) {
            while (!data.isEmpty() && val > data.peek()) {
                helper.push(data.pop());
            }
            data.push(val);
            while (!helper.isEmpty()) {
                data.push(helper.pop());
            }
        }

        public void pop() {
            if (!data.isEmpty()) {
                data.pop();
            }
        }

        public int peek() {
            if (data.isEmpty()) {
                return -1;
            }
            return data.peek();
        }

        public boolean isEmpty() {
            return data.isEmpty();
        }
    }
}
