package recursion;

public class MazeDemo {
    public static void main(String[] args) {
        //用二维数组模拟一个迷宫
        int[][] maze = new int[8][7];
        //使用1表示墙，上下全设置为1
        for (int i = 0; i < 7; i++) {
            maze[0][i] = 1;
            maze[7][i] = 1;
        }
        //左右设置为1
        for (int i = 1; i <= 6; i++) {
            maze[i][0] = 1;
            maze[i][6] = 1;
        }
        //设置挡板
        maze[3][1] = 1;
        maze[3][2] = 1;
        maze[6][4] = 1;
        maze[5][4] = 1;
        maze[5][5] = 1;

        //走迷宫
        go(maze,1,1);

        //输出地图
        for (int[] ints : maze) {
            for (int anInt : ints) {
                System.out.print(anInt+"  ");
            }
            System.out.println();
        }

    }

    //使用递归回溯来给小球找路
    //说明
    //1. map 表示地图
    //2. i,j表示从地图的哪个位置开始出发(1,1)
    //3.如果小球能到map[6][5]位置， 则说明通路找到。
    //4.约定:当map[i][j] 为0表示该点没有走过; 当为1表示墙; 2表示通路可以走; 3 表示该点已经走过,但是走不通
    //5.在走迷宫时，需要确定一个策略(方法)下->右->上->左 ，如果该点走不通，再回溯

    /**
     * @param map 地图
     * @param i   i和j确定位置
     * @param j
     * @return 这个迷宫是否可以走通
     */
    public static boolean go(int[][] map, int i, int j) {
        if (map[6][5] == 2) {       //找到通路
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;      //假设从这个点出发可以成功，先赋值为2
                if (go(map, i + 1, j)) {    //向下走
                    return true;
                } else if (go(map, i, j + 1)) { //向右走
                    return true;
                } else if (go(map, i - 1, j)) {     //向上走
                    return true;
                } else if (go(map, i, j - 1)){      //向左走
                    return true;
                }else {
                    //上面的方向都走不通，赋值为3表示从这个点出发找不到通路
                    map[i][j] = 3;
                    return false;
                }
            }else {
                //如果不是0，只可能是1，2，3，不管是哪个都是不能走的
                return false;
            }
        }
    }

}
