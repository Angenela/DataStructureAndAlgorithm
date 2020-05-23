package algorithm;

public class BinarySearchDemo {
    public static void main(String[] args) {
        int array[] = {1,2,3,4,5};
        int index = binarySearch(array, 4);
        System.out.println(index);
    }

    // 二分查找的非递归形式
    public static int binarySearch(int array[], int target) {
        int left = 0;
        int right = array.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            }
        }

        return -1;
    }
}
