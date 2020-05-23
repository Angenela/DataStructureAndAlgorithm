package sort;

public class QuickSortDemo {
    public static void main(String[] args) {
        int max = 8000000;

        int[] data = new int[max];

        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        long time01 = System.currentTimeMillis();
        quickSort(data, 0, data.length - 1);
        long time02 = System.currentTimeMillis();

        System.out.println("耗费的时间：" + (time02 - time01) + "毫秒");
    }

    public static void quickSort(int[] data, int left, int right) {
        int temLeft = left;
        int temRight = right;
        int temValue;
        int compareValue = data[(left + right) / 2];
        while (temLeft < temRight) {

            while (data[temLeft] < compareValue) {
                temLeft++;
            }

            while (data[temRight] > compareValue) {
                temRight--;
            }

            // 经过这个判断，就过滤了temLeft和temRight共同指向分隔值的情况
            if (temLeft >= temRight) {
                break;
            }

            temValue = data[temLeft];
            data[temLeft] = data[temRight];
            data[temRight] = temValue;

            // 这个判断就是为了可以越过和分隔值相等的情况
            // 在这相等，可能在分隔值左边有和分隔值相等的元素或temLeft指向分隔值
            // 此时temRight只可能指向和分隔值相等的元素或大于分隔值的元素
            if (data[temLeft] == compareValue) {
                temRight--;
            }
            //效果和上面的一样
            if (data[temRight] == compareValue) {
                temLeft++;
            }
        }

        // 此时temLeft和temRight是相等的或temLeft大于temRight
        // 如果是大于，只有一种可能，temLeft和temRight指向两个和分隔值相等的元素，其中一个有可能是分隔值

        // -1和+1是为了跳过分隔值，分隔值是不需要进入排序的
        // 左循环
        if (temRight > left) {
            quickSort(data, left, temRight - 1);
        }

        // 右循环
        if (temRight < right) {
            quickSort(data, temRight + 1, right);
        }
    }
}
