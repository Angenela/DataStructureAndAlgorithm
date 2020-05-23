package sort;

public class SelectSortDemo {
    public static void main(String[] args) {
        int max = 80000;

        int[] data = new int[max];

        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        long time01 = System.currentTimeMillis();
        selectSort(data);
        long time02 = System.currentTimeMillis();

        for (int datum : data) {
            System.out.print(datum + " ");
        }
        System.out.println();
        System.out.println("耗费的时间：" + (time02 - time01) + "毫秒");
    }

    public static void selectSort(int[] data) {

        int minIndex;
        int min;
        int tem;

        for (int i = 0; i < data.length - 1; i++) {
            minIndex = i;
            min = data[minIndex];
            for (int j = i + 1; j < data.length; j++) {
                if (min > data[j]) {
                    min = data[j];
                    minIndex = j;
                }
            }
            tem = data[minIndex];
            data[minIndex] = data[i];
            data[i] = tem;
        }
    }
}
