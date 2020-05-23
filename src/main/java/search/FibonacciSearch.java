package search;

import java.util.Arrays;

public class FibonacciSearch {

    public static int max = 20;

    public static void main(String[] args) {
        int array[] = new int[]{1, 8, 10, 89, 1000, 1234};
        int result = fibonacciSearch(array, 1);
        System.out.println(result);
    }

    public static int[] fibonacciArray() {
        int[] array = new int[20];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i < max; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }

        return array;
    }

    public static int fibonacciSearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        int k = 0; //表示斐波那契分割数值的下标
        int mid = 0; //存放mid值
        int f[] = fibonacciArray(); //获取到斐 波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类,构造一个新的数组，并指向array[]
        //不足的部分会使用0填充
        int[] temp = Arrays.copyOf(array, f[k]);
        //实际上需求使用array数组最后的数填充temp
        //举例:
        //temp = {1,8, 10，89，1000， 1234, 0, 0, 0} => {1,8, 10，89，1000， 1234， 1234， 1234, 1234}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = array[high];
        }
        //使用while来循环处理,找到我们的数key
        while (low <= high) { //只要这个条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (target < temp[mid]) { //我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                //为和是k--
                //说明
                //1.全部元素=前面的元素+后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //因为前面有f[k-1]个元素,所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid = f[k-1-1] -1
                k--;
            } else if (target > temp[mid]) { //我们应该继续向数组的后面查找(右边)
                low = mid + 1;
                //为什么是k -=2
                //说明
                //1.全部元素=前面的元素+后边元素
                //2. f[k] = f[k-1] + f[k-2]
                //3.因为后面我们有f[k-2]所以可以继续拆分f[k-1] = f[k-3] + f[k-4]
                //4.即在f[k-2] 的前面进行查找k -=2
                //5.即下次循环mid=f[k-1-2]-1
                k -= 2;
            } else {
                // 需要确定返回哪个值
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }

        return -1;
    }
}