package com.sinjinsong.alogrithm.stackqueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author sinjinsong
 * @date 2018/2/22
 */
public class ArrayToMaxTree {
    /**
     * 给定一个没有重复元素的数组arr，将arr转为一颗二叉树、
     * 该二叉树要求满足如下性质：
     * 1）数组的每一个值对应二叉树的一个节点
     * 2）包括整棵树在内的每一棵子树，值最大的节点都是根节点。
     * 这种二叉树和大顶堆很类似，只是大顶堆还要求必须是完全二叉树。
     * 最优解的时间复杂度为O(n)，空间复杂度为O(n)
     * 算法：
     * 1）需要计算每个数左边第一个比它大的数和右边第一个比它小的数。
     * 求leftFirstUpper（每个数左边第一个比它大的数），要求栈从栈底到栈顶是从大到小的序列。
     * rightFirstUpper同理，只是需要逆序遍历
     * 2）将1）中的表转为父子关系表：
     * 遍历数组，如果左右第一个比它大的有不为空的，就取不为空的数作为其父节点；
     * 如果都不为空，那么取值较小的数作为其父节点
     * 
     * @param arr
     * @return 每个元素为原数组中对应位置元素在树中的父亲节点的编号，若为根则值为-1
     */
    public static int[] buildMaxTree(int[] arr) {
        // 注意，arr[i]的左边第一个比它大的数的下标为leftFirstUpper[i],
        // 右边第一个比它大的数的下标为rightFirstUpper[i]
        int[] leftFirstUpperValueIndex = new int[arr.length];
        int[] rightFirstUpperValueIndex = new int[arr.length];
        Arrays.fill(leftFirstUpperValueIndex, -1);
        Arrays.fill(rightFirstUpperValueIndex, -1);
        // 该栈的栈顶是整个栈中的对应的数值中的最小值的索引
        // 入栈元素要求比栈顶还要小
        Stack<Integer> indexMinStack = new Stack<>();
        // 1)求得两张表
        for (int i = 0; i < arr.length; i++) {
            while (!indexMinStack.isEmpty() && arr[i] > arr[indexMinStack.peek()]) {
                indexMinStack.pop();
            }
            // 此时arr[i]一定小于栈顶
            // 记录
            if (!indexMinStack.isEmpty()) {
                leftFirstUpperValueIndex[i] = indexMinStack.peek();
            }
            indexMinStack.push(i);
        }
        indexMinStack.clear();
        // 逆序遍历
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!indexMinStack.isEmpty() && arr[i] > arr[indexMinStack.peek()]) {
                indexMinStack.pop();
            }
            // 此时arr[i]一定小于栈顶
            // 记录
            if (!indexMinStack.isEmpty()) {
                rightFirstUpperValueIndex[i] = indexMinStack.peek();
            }
            indexMinStack.push(i);
        }
        // 2)根据两张表求得树节点之间的父子关系
        int[] parent = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            parent[i] = getLowerValueIndex(arr, leftFirstUpperValueIndex[i], rightFirstUpperValueIndex[i]);
        }
        return parent;
    }

    private static int getLowerValueIndex(int[] arr, int leftFirstUpperValueIndex, int rightFirstUpperValueIndex) {
        int leftValue = getValue(arr, leftFirstUpperValueIndex);
        int rightValue = getValue(arr, rightFirstUpperValueIndex);
        // 优先返回不为空的，都不为空的话就返回较小数值的索引
        if (leftValue != -1 && rightValue != -1) {
            return leftValue < rightValue ? leftFirstUpperValueIndex : rightFirstUpperValueIndex;
        } else {
            return leftValue != -1 ? leftFirstUpperValueIndex : rightFirstUpperValueIndex;
        }
    }

    private static int getValue(int[] arr, int i) {
        if (i == -1) {
            return -1;
        } else {
            return arr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {36,1173,934,436};
        int[] parent = buildMaxTree(arr);
        System.out.println(Arrays.toString(parent));
    }
}
