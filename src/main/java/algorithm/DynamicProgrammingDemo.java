package algorithm;

import java.util.Arrays;

public class DynamicProgrammingDemo {
    public static void main(String[] args) {
        int weight[] = {1, 4, 3};       // 物品的重量
        int value[] = {1500, 3000, 2000};       // 物品的价值
        int max[][] = new int[4][5];// max[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值，空出第一行和第一列
        int path[][] = new int[4][5];   // 记录每个物品的数量


        for (int i = 1; i < max.length; i++) {
            for (int j = 1; j < max[0].length; j++) {       // j 为背包的容量
                if (weight[i - 1] > j) {
                    max[i][j] = max[i - 1][j];
                }
                if (j >= weight[i - 1]) {       // 在这里计算最优的情况
//                    max[i][j] = Math.max(max[i - 1][j], max[i - 1][j - weight[i - 1]] + value[i - 1]);
                    if (max[i - 1][j] < max[i - 1][j - weight[i - 1]] + value[i - 1]) {
                        max[i][j] = max[i - 1][j - weight[i - 1]] + value[i - 1];
                        path[i][j] = 1;
                    } else {
                        max[i][j] = max[i - 1][j];
                    }
                }
            }
        }

        for (int[] ints : max) {
            System.out.println(Arrays.toString(ints));
        }


        System.out.println("==============================================");
        // 这里是打印出背包可以存储 4 个重量时的情况
        int i = max.length - 1;
        int j = max[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1){
                System.out.println("将"+i+"个商品放入背包");
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
