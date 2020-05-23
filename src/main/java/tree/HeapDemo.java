package tree;

import java.util.Arrays;

public class HeapDemo {
    public static void main(String[] args) {
        int max = 8000;
        int[] arr = new int[max];

        for (int i = 0; i < max; i++) {
            arr[i] = (int) (Math.random() * 100);
        }

        long time1 = System.currentTimeMillis();
        heapSort(arr);
        long time2 = System.currentTimeMillis();

        System.out.println("耗时  " + (time2 - time1) + "  毫秒\n");
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int arr[]) {
        int length = arr.length;
        int temp;

        // 这个for循环将数组变成一个大顶堆
        for (int i = length / 2 - 1; i >= 0; i--) { // i 总是指向叶子节点
            heapAdjust(arr, i, length);
        }

        // 这个时候大的元素都在根节点附近，小的元素都在数组的后面
        // 这个循环就是将大的元素和小的元素交换
        for (int j = length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            heapAdjust(arr, 0, j);
        }

    }

    /**
     * 此方法的作用是为了将子树调整为大顶堆，过程类似选择排序
     *
     * @param arr    调整的数组
     * @param index  需要调整的非叶子节点
     * @param length 数组的长度
     */
    public static void heapAdjust(int arr[], int index, int length) {
        int temp = arr[index];

        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            if (i + 1 < length && arr[i] < arr[i + 1]) {     // 比较左节点和右节点的大小
                i++;
            }
            if (arr[i] > temp) { // 比较最大子节点和父节点的大小
                arr[index] = arr[i];
                index = i;
            } else {
                break;
            }
        }

        // for循环后index指向最大的子节点
        arr[index] = temp;
    }

}
