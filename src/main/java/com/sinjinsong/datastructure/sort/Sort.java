package com.sinjinsong.datastructure.sort;

import java.util.Arrays;

public class Sort {

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        boolean isSorted;
        T temp;
        for (int i = 0; i < arr.length - 1; i++) {
            isSorted = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    isSorted = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    public static <T extends Comparable<T>> void insertSort(T[] arr) {
        int sortedIndex = 0;
        T guard = null;
        for (int unSortedIndex = 1; unSortedIndex < arr.length; unSortedIndex++) {
            // unSortedIndex从无序区的第一个元素开始(默认开始时因为只有一个元素，所以第一个元素是有序的)
            guard = arr[unSortedIndex];// 将无序区的第一个元素放在岗哨中
            sortedIndex = unSortedIndex - 1; // sortedIndex是有序区的最后一个元素
            while (sortedIndex >= 0 && arr[sortedIndex].compareTo(guard) > 0) {
                // 如果有序区的最后一个元素比岗哨大，那么后移，直到岗哨找到适合它的位置为止
                arr[sortedIndex + 1] = arr[sortedIndex];
                sortedIndex--;
            }
            arr[sortedIndex + 1] = guard; // j+1这个位置空了出来，然后将岗哨赋给j-1这个位置上
        }
    }

    public static <T extends Comparable<T>> void binaryInsertSort(T[] arr) {
        int low = 0, high = 0, mid = 0;
        T guard = null;
        for (int unSortedIndex = 1; unSortedIndex < arr.length; unSortedIndex++) {
            // i从无序区的第一个元素开始(默认开始时因为只有一个元素，所以第一个元素是有序的)
            guard = arr[unSortedIndex];// 将无序区的第一个元素放在岗哨中
            low = 0;
            high = unSortedIndex - 1;// low~high指向整个有序区
            while (low <= high) {
                mid = (low + high) / 2;
                if (arr[mid].compareTo(guard) > 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // 结束后low指向待插入元素的位置，需要将low及之后的元素(至有序区的最后一个元素unSortedIndex-1)向后移
            for (int i = unSortedIndex - 1; i >= low; i--) {
                arr[i + 1] = arr[i];
            }
            arr[low] = guard;
        }
    }

    public static <T extends Comparable<T>> void shellSort(T[] arr) {
        int movIdx = 0;// 移动时候的索引
        T guard = null;
        // 增量从长度的一半开始，最终会减到1，减到0时结束
        for (int inc = arr.length / 2; inc > 0; inc /= 2) {
            for (int i = inc; i < arr.length; i++) {
                // i从第一组的第二个元素（第一个与第二个相差增量）开始
                guard = arr[i];// 待插入元素
                for (movIdx = i - inc; movIdx >= 0 && guard.compareTo(arr[movIdx]) < 0; movIdx -= inc) {
                    arr[movIdx + inc] = arr[movIdx];
                } // 跳跃式的移动
                arr[movIdx + inc] = guard;
            }
        }
    }

    public static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        // 只有有多于一个的元素才进行排序
        if (left < right) {
            int low = left, high = right;
            // 此时low==high等于枢轴index,此时小于等于枢轴的都放到了low的左侧，大于枢轴的都放到了low的右侧
            low = partition(arr, low, high);
            quickSort(arr, left, low - 1);// 分而治之
            quickSort(arr, low + 1, right);
        }
    }

    private static <T extends Comparable<T>> int partition(T[] arr, int low, int high) {
        T pivotKey = arr[low];// 枢轴
        while (low < high) {
            while (low < high && arr[high].compareTo(pivotKey) >= 0) {
                high--;
            }
            // high向左移动，直至遇到小于枢轴的元素，然后将high所指向元素赋给low指向元素（也就是已经保存下来的元素，不会丢失）
            arr[low] = arr[high];
            while (low < high && arr[low].compareTo(pivotKey) <= 0) {
                low++;
            }
            // low向右移动，直至遇到大于枢轴的元素，然后将low所指向元素赋给high所指向元素
            arr[high] = arr[low];
        }
        // 退出时low和high都指向同一元素（中间的一个位置），将枢轴赋给这个元素
        // 并且在枢轴的左面的元素都比枢轴小，在枢轴右面的元素都比枢轴大
        arr[low] = pivotKey;
        return low;
    }

    public static <T extends Comparable<T>> void selectSort(T[] arr) {
        int k = 0;
        T t = null;
        // 有序区在前，无序区在后
        for (int i = 0; i < arr.length - 1; i++) {// 最后一个元素自动有序
            k = i;// k保存i的值，指向有序区的最后一个元素
            for (int j = i + 1; j < arr.length; j++) {// j指向无序区的第一个元素
                if (arr[k].compareTo(arr[j]) > 0) {
                    k = j;// k始终指向最小元素
                }
            }
            if (k != i) {
                t = arr[i];
                arr[i] = arr[k];
                arr[k] = t;
            }
            // 交换无序区中最小元素和有序区的最后一个元素
        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] arr) {
        T t = null;
        for (int i = arr.length / 2; i >= 0; i--) {
            filterDown(arr, arr.length, i);// 建立大顶堆，每次调用都排序一棵二叉树，如果还有子树那么继续调整
        }
        for (int i = arr.length - 1; i >= 1; i--) {
            t = arr[0];
            arr[0] = arr[i];
            arr[i] = t;
            filterDown(arr, i, 0);// 先将堆顶元素与堆的无序区的最后一个元素交换，然后重新调整为堆(每次循环后都实现了一个元素的有序，因此调整的范围减一)
        }
    }

    private static <T extends Comparable<T>> void filterDown(T[] data, int size, int root) {
        T e = data[root];
        int child = 0;
        while ((root * 2 + 1) < size) {//当存在左孩子时
            child = root * 2 + 1;
            //如果还有右孩子，则比较左孩子和右孩子
            if ((child + 1) < size && data[child].compareTo(data[child + 1]) < 0) {
                child++;
            }
            // child指向左右孩子较大的
            if (e.compareTo(data[child]) > 0) {
                break;// 符合大顶堆的规则，直接退出
            } else {
                data[root] = data[child];
                root = child;// 继续向下调整
            }
        }
        data[root] = e;
    }

    // 实现从low至high的排序
    public static <T extends Comparable<T>> void mergeSort(T[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    // 实现low~mid 和 mid+1~high的合并，合并后是有序的
    public static <T extends Comparable<T>> void merge(T[] arr, int low, int mid, int high) {
        T[] mergeArr = arr.clone();
        int left = low;// 左半边的指针
        int right = mid + 1;// 右半边的指针
        int mergeIndex = low;// 指向归并数组的指针
        while (left <= mid && right <= high) {
            if (arr[left].compareTo(arr[right]) < 0) {
                // 左半边的元素小于右半边的元素
                mergeArr[mergeIndex] = arr[left];
                left++;
            } else {
                mergeArr[mergeIndex] = arr[right];
                right++;
            }
            mergeIndex++;
        }
        // 将左半边或右半边还没有放到归并数组中的放入
        while (left <= mid) {
            mergeArr[mergeIndex] = arr[left];
            mergeIndex++;
            left++;
        }
        // 将左半边或右半边还没有放到归并数组中的放入
        while (right <= high) {
            mergeArr[mergeIndex] = arr[right];
            mergeIndex++;
            right++;
        }
        // 将归并数组中的元素复制回原数组，注意二者下标是相同的
        while (low <= high) {
            arr[low] = mergeArr[low];
            low++;
        }
    }

    // 基数排序，radix是基数（关键字个数），digit是数字的最高位数
    // 仅能用来给数字排序
    public static void radixSort(int[] arr, int radix, int digit) {
        int[] count = new int[radix];// 用于计算每个基数对应的各个元素所占bucket的索引位置
        int[] bucket = new int[arr.length];// 用于存放一趟排序之后的数据
        int rate = 1;// rate是用来求数据的基数用的
        int key = 0;// key是用来保存基数的
        for (int d = 1; d <= digit; d++) {
            for (int i = 0; i < count.length; i++) {
                count[i] = 0;
            }
            // 每次遍历前清空count数组
            // 对数组中的数据的基数进行计数，得到每个基数的元素个数，为计算存放它们的下表（右边界）做准备
            for (int i = 0; i < bucket.length; i++) {
                key = arr[i] / rate % radix;// key就是arr【i】这个元素的倒数第d位的值
                count[key]++;
            }
            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];// 得到每个基数存放的右界，基数为i存放在count[i]~count[i+1]-1处
            }
            // 为了保持排序的稳定性（比如十位排序后保持十位相同时个位的顺序），从后往前排序
            for (int i = arr.length - 1; i >= 0; i--) {
                key = arr[i] / rate % radix;
                bucket[count[key] - 1] = arr[i];
                count[key]--;
            }
            // 将bucket暂存的数据重新赋回arr
            for (int i = 0; i < arr.length; i++) {
                arr[i] = bucket[i];
            }
            rate *= radix;// 如果这次是个位，那么下次就是十位，rate*radix
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {50, 123, 543, 187, 49, 30, 0, 2, 11, 100};
//		QuickSort.radixSort(arr,10,3);
//		QuickSort.heapSort(arr);
//        shellSort(arr);
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
