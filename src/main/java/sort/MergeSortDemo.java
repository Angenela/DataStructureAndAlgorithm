package sort;

public class MergeSortDemo {
    public static void main(String[] args) {
        int max = 80000;

        int[] data = new int[max];

        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        long time01 = System.currentTimeMillis();
        mergeSort(data, 0, data.length - 1);
        long time02 = System.currentTimeMillis();

        System.out.println("耗费的时间：" + (time02 - time01) + "毫秒");
    }

    // 分割
    public static void mergeSort(int[] data, int left, int right) {
        int[] array = new int[(right - left) + 1];    // 临时数组，放入有序数据
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(data, left, mid);
            mergeSort(data, mid + 1, right);
            merge(data, left, mid, right, array);
        }

    }

    // 合并
    /**
     * @param data  数组
     * @param left  左数组的左下标
     * @param mid   左数组的右下标
     * @param right 右数组的右下标
     */
    public static void merge(int[] data, int left, int mid, int right, int[] array) {
        int index = mid + 1;     // 左数组的右下标
        int arrayIndex = 0;     // array的下标
        int temLeft = left;

        // 依次比较两个数组的值，将小的放入有序表array
        while (temLeft <= mid && index <= right) {
            if (data[temLeft] < data[index]) {
                array[arrayIndex] = data[temLeft];
                arrayIndex++;
                temLeft++;
            } else {
                array[arrayIndex] = data[index];
                arrayIndex++;
                index++;
            }
        }

        // 将右数组剩余的数据添加到array
        while (temLeft <= mid) {
            array[arrayIndex] = data[temLeft];
            arrayIndex++;
            temLeft++;
        }

        // 将左数组剩余的数据添加到array
        while (index <= right) {
            array[arrayIndex] = data[index];
            arrayIndex++;
            index++;
        }

        // 将array的数据复制到data
        temLeft = left;
        arrayIndex = 0;
        while (temLeft <= right) {
            data[temLeft] = array[arrayIndex];
            temLeft++;
            arrayIndex++;
        }
    }
}
