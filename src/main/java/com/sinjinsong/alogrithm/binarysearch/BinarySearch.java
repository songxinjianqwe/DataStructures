package com.sinjinsong.alogrithm.binarysearch;

/**
 * @author sinjinsong
 * @date 2018/2/23
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int binarySearchMinPosition(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int result = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key <= arr[mid]) {
                high = mid - 1;
                if (key == arr[mid]) {
                    result = mid;
                }
            } else {
                low = mid + 1;
            }
        }
        return result;
    }


    public static int binarySearchMaxPosition(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int result = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key >= arr[mid]) {
                low = mid + 1;
                if (key == arr[mid]) {
                    result = mid;
                }
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,7,12,14,23,33,37,45,59,59,61,62,67,69,76,77,81,83,86,98,99,99,102,105,106,112,118,119,125,131,132,137,141,141,142,145,159,161,161,161,165,173,180,181,183,184,189,190,212,222,223,223,229,241,243,244,247,247,254,255,255,258,259,260,267,272,273,276,277,283,288,291,293,294,296,297,297,300,302,309,312,312,316,318,323,327,329,334,334,341,349,349,351,354,365,377,377,377,378,379,386,387,387,391,394,405,416,418,418,423,424,425,427,428,429,431,434,435,443,443,444,452,455,465,477,484,489,493,493,502,505,507,517,521,527,528,546,548,548,551,551,555,556,562,572,581,595,605,613,620,627};
//        System.out.println(binarySearch(arr, 4));
        System.out.println(binarySearchMinPosition(arr, 1));
//        System.out.println(binarySearchMaxPosition(arr, 3));
    }
} 
