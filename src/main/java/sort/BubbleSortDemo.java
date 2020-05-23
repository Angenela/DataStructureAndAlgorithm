package sort;

public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] test = new int[80000];

        for (int i =0; i<80000; i++){
            test[i] = (int) (Math.random() * 100);
        }


        long time01 = System.currentTimeMillis();
//        bubbleSort(test);  //14515  毫秒
        bubbleSortPlus(test);  //11074  毫秒
        long time02 = System.currentTimeMillis();


        for (int i : test) {
            System.out.print(i+" ");
        }

        System.out.println();
        System.out.println("--------------");
        System.out.println("时间消耗为"+(time02-time01)+"毫秒");
    }


    //Plus版最不同的是第一层循环，你细品
    public static void bubbleSortPlus(int[] data) {
        //用于判断数组是否在执行完之前就已经有序了
        boolean decide;
        //交换时的临时值
        int tem;
        for (int i = data.length - 1; i >= 0; i--) {
            decide = true;
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    //发生了互换
                    decide = false;
                    tem = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tem;
                }
            }
            //如果没有发生一次互换则说明已经有序可以直接退出
            if (decide) {
                break;
            }
        }
    }

    public static void bubbleSort(int[] data) {
        //用于判断数组是否在执行完之前就已经有序了
        boolean decide;
        //交换时的临时值
        int tem;
        for (int i = 0; i < data.length - 1; i++) {
            decide = true;
            for (int j = 0; j < data.length - 1; j++) {
                if (data[j] > data[j + 1]) {
                    //发生了互换
                    decide = false;
                    tem = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tem;
                }
            }
            //如果没有发生一次互换则说明已经有序可以直接退出
            if (decide) {
                break;
            }
        }
    }
}
