package com.sinjinsong.alogrithm.stackqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author sinjinsong
 * @date 2018/2/27
 */
public class MedianOfStream {
    private int count = 0;
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });

    /**
     * 把所有的数字分为两部分，数据总数为偶数时，新加入的元素与大根堆中所有元素比较，最大值进入小根堆
     * 数据总数为奇数时，新加入的元素与小根堆中所有元素比较，最小值进入大根堆
     * 结果就是：大根堆存放较小的一部分数字，小根堆存放较大的一部分数字
     * 当数据总数为偶数时，中位数为(大根堆顶+小根堆顶)/2
     * 当数据总数为奇数时，小根堆总数比大根堆多一个，中位数即为小根堆顶
     *
     * @param num
     */
    public void insert(int num) {
        if ((count & 1) == 0) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }else{
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }
        count++;
    }

    public double getMedian() {
        if((count & 1) == 0){
            return 1.0 * (minHeap.peek() + maxHeap.peek()) / 2;
        }else{
            return 1.0 * minHeap.peek();
        }
    }
}
