package algorithm;

public class DivideAndConquerDemo {
    public static void main(String[] args) {
        hanoi(5, 'A', 'B', 'C');
    }

    public static void hanoi(int count, char a, char b, char c) {
        if (count == 1) {
            System.out.println("第 1 个盘子从 " + a + "--->" + c);
        } else {
            hanoi(count - 1, a, c, b);
            System.out.println("第 " + count + " 个盘子从 " + a + "--->" + c);
            hanoi(count - 1, b, a, c);
        }
    }
}
