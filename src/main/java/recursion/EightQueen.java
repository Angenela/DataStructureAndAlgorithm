package recursion;

public class EightQueen {

    //用来保存皇后的位置
    int[] array = new int[8];
    //计算一共几种摆法
    int count = 0;

    public static void main(String[] args) {
        EightQueen eightQueen = new EightQueen();
        eightQueen.chess(0);
        System.out.println(eightQueen.count);
    }


    //摆放第n个皇后
    public void chess(int n){
        if (n == 8){    //array是从0开始的
            print();
            count++;
            return;
        }

        for (int i=0; i<8; i++){
            array[n] = i;
            if (judge(n)){
                chess(n+1);
            }
        }
    }

    //摆放第count个皇后后去判断是否可以摆放在这个位置
    /**
     *
     * @param count 第几个皇后
     * @return
     */
    public boolean judge(int count){
        for (int i = 0; i<count; i++){
            //判断第count个皇后和前面的皇后是否在同一列或同一斜线上
            if (array[i] == array[count] || (count - i) == Math.abs(array[i] - array[count])){
                return false;
            }
        }

        return true;
    }

    //打印array保存的位置
    public void print(){
        for (int i : array) {
            System.out.print(i+1+"  ");
        }
        System.out.println();
    }
}
