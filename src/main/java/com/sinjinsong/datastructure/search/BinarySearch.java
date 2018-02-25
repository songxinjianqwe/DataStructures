package com.sinjinsong.datastructure.search;

/**
 * @author sinjinsong
 * @date 2018/2/13
 */
public class BinarySearch {


    public static <T extends Comparable<T>> int binarySearch(T[] arr, T key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key.compareTo(arr[mid]) > 0) {
                low = mid + 1;
                // 如果key比中间值大，那么取右半部分
            } else if (key.compareTo(arr[mid]) < 0) {
                high = mid - 1;
                // 如果key比中间值小，那么取左半部分
            } else {
                return mid;
            }
        }
        // 当low>high 且 该索引处的值不为e，则表示没有找到
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearchMinPosition(T[] arr, T key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int result = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key.compareTo(arr[mid]) <= 0) {
                // 找到相等的不会立即结束，而是继续向更低的位置二分查找
                high = mid - 1;
                if (key.compareTo(arr[mid]) == 0) {
                    result = mid;
                }
            } else {
                low = mid + 1;
            }
        }
        // 当start>end 且 该索引处的值不为e，则表示没有找到
        return result;
    }

    public static <T extends Comparable<T>> int binarySearchMaxPosition(T[] arr, T key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int result = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key.compareTo(arr[mid]) >= 0) {
                low = mid + 1;
                if (key.compareTo(arr[mid]) == 0) {
                    result = mid;
                }
            } else {
                high = mid - 1;
            }
        }
        // 当start>end 且 该索引处的值不为e，则表示没有找到
        return result;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 4, 4, 4, 4, 5, 7, 10};
//        System.out.println(binarySearch(arr, 4));
//        System.out.println(binarySearchMinPosition(arr, 4));
//        System.out.println(binarySearchMaxPosition(arr, 4));
    }
}
