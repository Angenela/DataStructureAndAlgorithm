package sort;

public class BarrelSortDemo {
    public static void main(String[] args) {
        int max = 80000;

        int[] data = new int[max];
        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        long time01 = System.currentTimeMillis();
        barrelSort(data);
        long time02 = System.currentTimeMillis();

        System.out.println("耗费的时间：" + (time02 - time01) + "毫秒");
    }

    public static void barrelSort(int[] array) {
        int[][] barrels = new int[10][array.length];   // 创建桶
        int[] barrelsCount = new int[10];   // 记录桶里的元素个数
        int max = array[0];     // 获取最大的数用来获取最大的位数

        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }

        int length = (max + "").length();   // 获取位数

        for (int k = 1; k <= length; k++) {

            // 此循环将数加入桶中
            for (int i = 0; i < array.length; i++) {
                int number = (array[i] / (k * 10)) % 10;
                barrels[number][barrelsCount[number]] = array[i];
                barrelsCount[number]++;
            }

            int index = 0;
            // 此循环将数取出
            for (int i = 0; i < barrels.length; i++) {
                for (int j = 0; j < barrelsCount[i]; j++) {
                    array[index] = barrels[i][j];
                    index++;
                }
                barrelsCount[i] = 0;   // 为了下一次的循环而清零
            }
        }
    }
}
