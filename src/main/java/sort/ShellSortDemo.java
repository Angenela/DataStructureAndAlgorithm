package sort;

public class ShellSortDemo {
    public static void main(String[] args) {
        int max = 80000;

        int[] data = new int[max];
        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        long time01 = System.currentTimeMillis();
        shellSortPlus(data);
        long time02 = System.currentTimeMillis();

        System.out.println("耗费的时间：" + (time02 - time01) + "毫秒");
    }

    // 希尔排序的交换式，效率不高
    public static void shellSort(int[] data) {
        int tem;
        for (int i = data.length / 2; i > 0; i = i / 2) {
            for (int j = i; j < data.length; j++) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (data[k] > data[k + i]) {
                        tem = data[k];
                        data[k] = data[k + i];
                        data[k + i] = tem;
                    }
                }
            }
        }
    }

    // 希尔排序的移动式
    public static void shellSortPlus(int[] data) {

        int value;
        int index;

        // 增量gap,并逐步的缩小增量
        for (int gap = data.length / 2; gap > 0; gap = gap / 2) {   // 分割的间隔

            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int j = gap; j < data.length; j++) {

                // 这里的代码类似于选择排序
                index = j;
                value = data[index];
                if (data[index] < data[index - gap]) {
                    //这里多判断了data[index] < data[index - gap]，不过为了简洁还是忍了
                    while (index - gap >= 0 && value < data[index - gap]){
                        data[index] = data[index - gap];
                        index -= gap;
                    }
                    data[index] = value;
                }

            }
        }
    }
}
