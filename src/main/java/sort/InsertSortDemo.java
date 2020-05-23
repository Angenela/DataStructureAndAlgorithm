package sort;

public class InsertSortDemo {
    public static void main(String[] args) {
        int max = 80000;

        int[] data = new int[max];

        for (int i = 0; i < max; i++) {
            data[i] = (int) (Math.random() * 100);
        }

        long time01 = System.currentTimeMillis();
        insertSort(data);
        long time02 = System.currentTimeMillis();

        for (int datum : data) {
            System.out.print(datum + " ");
        }
        System.out.println();
        System.out.println("耗费的时间：" + (time02 - time01) + "毫秒");
    }

    //1. 把这个数组看成一个有序组和一个无序组
    //2. 开始时有序组只有data[0]这个元素
    //3. 随着排序，有序组的元素越来越多，无序组的元素越来越少
    public static void insertSort(int[] data) {
        int insertValue;    //待插入有序组的无序组的第一个元素
        int insertIndex;    //有序组的最后一个元素的位置
        for (int i = 0; i < data.length - 1; i++) {
            insertValue = data[i + 1];      //保存无序组的第一个元素值
            insertIndex = i;
            //找到无序组的元素在有序组里比它小的，并将有序组元素后移一位
            while (insertIndex >= 0 && insertValue < data[insertIndex]) {
                data[insertIndex + 1] = data[insertIndex];
                insertIndex--;
            }
            //循环退出时，无序组的元素在有序组里找到比它小的了，加1是因为现在insertIndex指向比他小的元素
            data[insertIndex + 1] = insertValue;
        }
    }
}
