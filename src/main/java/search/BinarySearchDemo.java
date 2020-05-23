package search;

import java.util.ArrayList;

public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 5, 6, 8, 8, 9};
        ArrayList<Integer> integers = new ArrayList<Integer>();
        integers = binarySearchPlus(data, 8, 0, data.length - 1);
        System.out.println(integers);
    }

    public static int binarySearch(int[] array, int target, int left, int right) {
        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (left > right) {
            return -1;
        }

        if (target < midValue) {
            return binarySearch(array, target, mid - 1, left);
        } else if (target > midValue) {
            return binarySearch(array, target, mid + 1, right);
        } else {
            return mid;
        }
    }

    // 可以返回多个目标的index
    public static ArrayList binarySearchPlus(int[] array, int target, int left, int right) {
        int mid = (left + right) / 2;
        int midValue = array[mid];

        if (left > right) {
            return null;
        }

        if (target < midValue) {
            return binarySearchPlus(array, target, mid - 1, left);
        } else if (target > midValue) {
            return binarySearchPlus(array, target, mid + 1, right);
        } else {
            ArrayList<Integer> integers = new ArrayList<Integer>();
            integers.add(mid);
            // 向左边扫描
            int index = mid - 1;
            while (true) {
                if (array[index] != target || mid < 0) {
                    break;
                }
                integers.add(index);
                index--;
            }
            // 向右边扫描
            index = mid + 1;
            while (true) {
                if (array[index] != target || mid > right) {
                    break;
                }
                integers.add(index);
                index++;
            }

            return integers;
        }
    }
}
