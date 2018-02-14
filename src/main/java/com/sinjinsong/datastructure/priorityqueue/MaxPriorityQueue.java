package com.sinjinsong.datastructure.priorityqueue;

/**
 * 最大优先级队列：根节点权值比左右孩子都要大 并且是一个完全二叉树，使用数组存放
 * 第i号位置的父节点的位置是(i-1)/2取下界，左孩子是2i+1，右孩子是2i+2
 *
 * @author NewSong
 * @ClassName: MasPriorityQueue
 * @Description: 最大堆/大顶堆
 * @date 2016年11月28日 下午2:11:53
 */

public class MaxPriorityQueue<E extends Comparable<E>> {
    public static final int DEFAULT_CAPACITY = 20;
    private Object[] elements;
    private int currentSize;
    private int maxSize;

    public MaxPriorityQueue(int maxSize) {
        elements = new Object[maxSize];
        this.maxSize = maxSize;
    }

    public MaxPriorityQueue() {
        this(DEFAULT_CAPACITY);
    }

    public MaxPriorityQueue(E[] value) {
        this.elements = new Object[value.length];
        System.arraycopy(value, 0, elements, 0, value.length);
        maxSize = value.length;
        currentSize = value.length;
    }

    public MaxPriorityQueue(int size, E[] value) {
        if (size < value.length) {
            System.out.println("Input Error");
        }
        this.elements = new Object[size];
        System.arraycopy(value, 0, elements, 0, value.length);
        maxSize = size;
        currentSize = value.length;
    }

    // 将数据重新建堆
    public void buildHeap() {
        for (int i = (currentSize - 1) / 2; i >= 0; i--) {
            filterDown(currentSize - 1, i);// 建立大顶堆，每次调用都排序一棵二叉树，如果还有子树那么继续调整
        }
    }

    // 结果是升序
    // 删除n-1次，每次删除获得一个有序的元素
    public void heapSort() {
        Object temp = null;
        for (int i = currentSize - 1; i > 0; i--) {
            temp = elements[0];
            elements[0] = elements[i];
            elements[i] = temp;
            filterDown(i - 1, 0);// 先将堆顶元素与堆的无序区的最后一个元素交换，然后重新调整为堆(每次循环后都实现了一个元素的有序，因此调整的范围减一)
        }
    }

    public int size() {
        return currentSize;
    }

    public E peek() {
        return (E) elements[0];
    }

    public void offer(E e) {
        if (isFull()) {
            System.out.println("已满。无法插入");
            return;
        }
        elements[currentSize] = e;
        currentSize++;
        //child index
        filterUp(currentSize - 1);
    }

    public void clear() {
        for (int i = 0; i < elements.length; ++i) {
            elements[i] = null;
        }
        currentSize = 0;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public E poll() {
        if (isEmpty()) {
            System.out.println("堆为空，不可删除");
            return null;
        }
        E res = (E) elements[0];
        elements[0] = elements[currentSize - 1];
        currentSize--;
        filterDown(currentSize - 1, 0);
        return res;
    }

    // 将以传入的child为孩子的所在树调整为大顶堆，并递归向上调整
    private void filterUp(int child) {
        // 当孩子比父节点大，就交换
        Object element = elements[child];
        while (child != 0 && ((Comparable<E>) element).compareTo((E) elements[(child - 1) / 2]) > 0) {
            elements[child] = elements[(child - 1) / 2];
            child = (child - 1) / 2;
        }
        elements[child] = element;// 放入到合适位置
    }

    // 将以传入的root为根节点的所在树（根+左孩子+右孩子）调整为大顶堆，并递归向下调整
    private void filterDown(int maxIndex, int root) {
        E e = (E) elements[root];
        int upperChild;
        // 当root仍有孩子，则继续循环
        while ((root * 2 + 1) <= maxIndex) {
            upperChild = root * 2 + 1;
            if (upperChild < maxIndex && ((Comparable<E>) elements[upperChild]).compareTo((E) elements[upperChild + 1]) < 0) {
                upperChild++; 
            }
            // 此时upperChild指向左右孩子较大的
            if (e.compareTo((E) elements[upperChild]) > 0) {
                break;// 符合大顶堆的规则，直接退出
            } else {
                elements[root] = elements[upperChild];
                root = upperChild;// 继续向下调整
            }
        }
        elements[root] = e;
    }

    // 层序遍历
    public void print() {
        for (int i = 0; i < currentSize; ++i) {
            System.out.print(elements[i] + " ");
        }
        System.out.println();
    }

    //Done!
    public static void main(String[] args) {
        Integer[] data = {20, 12, 35, 15, 10, 80, 30, 17, 2, 1};
        MaxPriorityQueue<Integer> queue = new MaxPriorityQueue<>(20, data);
        queue.buildHeap();
        queue.print();

		queue.offer(32);
		queue.print();
		queue.poll();
		queue.print();
        queue.heapSort();
        queue.print();
    }
}
