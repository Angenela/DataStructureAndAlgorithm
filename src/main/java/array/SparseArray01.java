package array;

public class SparseArray01 {
    public static void main(String[] args) {
        //创建一-个 原始的二维数组11 * 11
        //0:表示没有棋子，1表示黑子2表蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[3][5] = 2;
        chessArr1[5][7] = 3;
        //输出chessAttr1
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        //1. 先读取稀疏数组的第一行， 根据第一行的数据，创建原始的二维数组
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        int dataIndex = 1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr[dataIndex][0] = i;
                    sparseArr[dataIndex][1] = j;
                    sparseArr[dataIndex][2] = chessArr1[i][j];
                    dataIndex++;
                }
            }
        }

        //输出chessAttr1
        System.out.println("---------------------------------");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

//        稀疏数组转原始的二维数组的思路：
//        1. 先读取稀疏数组的第一行， 根据第一行的数据，创建原始的二维数组
//        2. 在读取稀疏数组后几行的数据，并赋给原始的二维数组即可

    }
}
