package com.sinjinsong.leetcode.medium;

import java.util.Stack;

public class LeetCode1846 {
    /**
     * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
     * <p>
     * 示例1:
     * <p>
     * 输入：
     * ["SortedStack", "push", "push", "peek", "pop", "peek"]
     * [[], [1], [2], [], [], []]
     * 输出：
     * [null,null,null,1,null,2]
     * 示例2:
     * <p>
     * 输入：
     * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
     * [[], [], [], [1], [], []]
     * 输出：
     * [null,null,null,null,null,true]
     * 说明:
     * <p>
     * 栈中的元素数目在[0, 5000]范围内。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-of-stacks-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class SortedStack {
        private final Stack<Integer> data = new Stack<>();
        private final Stack<Integer> helper = new Stack<>();


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

    public void sort(Stack<Integer> stack) {
        Stack<Integer> helper = new Stack<>();
        while (!stack.isEmpty()) {
            if (helper.isEmpty()) {
                helper.push(stack.pop());
            } else {
                int val = stack.pop();
                while (!helper.isEmpty() && val > helper.peek()) {
                    stack.push(helper.pop());
                }
                helper.push(val);
            }
        }
        while (!helper.isEmpty()) {
            stack.push(helper.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(4);
        s.push(9);
        s.push(8);
        s.push(3);
        new LeetCode1846().sort(s);
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }
    }
}
