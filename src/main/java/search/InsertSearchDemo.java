package search;

public class InsertSearchDemo {
    public static void main(String[] args) {
        int max = 100;
        int[] data = new int[max];
        for (int i = 0; i < max; i++) {
            data[i] = i + 1;
        }
        int result;
        result = insertSearch(data, 0, data.length - 1, 43);
        System.out.println(result);
    }

    // 只有mid的求法不同，其它和二分查找一样
    public static int insertSearch(int array[], int left, int right, int target) {

        System.out.println("查找了一次");
        // 这些判断必须的，如果没有，在某些情况下会数组越界
        if (left > right || target < array[0] || target > array[array.length - 1]) {
            return -1;
        }

        int mid = left + (right - left) * (target - array[left]) / (array[right] - array[left]);

        if (array[mid] > target) {
            return insertSearch(array, left, mid - 1, target);
        } else if (target > array[mid]) {
            return insertSearch(array, mid + 1, right, target);
        } else {
            return mid;
        }
    }
}
