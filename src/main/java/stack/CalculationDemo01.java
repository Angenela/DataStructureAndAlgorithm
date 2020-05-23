package stack;

public class CalculationDemo01 {
    public static void main(String[] args) {
        String expression = "100/2-25+6000000";
        //创建两个栈，数栈，一个符号栈
        ArrayStackC numStack = new ArrayStackC(10);
        ArrayStackC operStack = new ArrayStackC(10);
        //定义需要的相关变量
        int index = 0;  //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        char ch = ' ';  //将每次扫描得到char保存到ch
        String moreNum = "";
        //开始while循环的扫描expression
        while (true) {
            //依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断是否是运算符
            if (operStack.isOperation(ch)) {
                //符号栈是否是空的
                if (!operStack.isEmpty()) {
                    //比较符号栈顶元素和ch的优先级, 如果小于等于就计算，之所以循环是因为符号栈的下一个也可能小于等于
                    //而且,可能计算完后符号栈就空了，因此要判空
                    while (!operStack.isEmpty() && operStack.priority(ch) <= operStack.priority(operStack.lookStackTop())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        int result = operStack.calculation(num2, num1, oper);
                        numStack.push(result);
                    }
                }
                operStack.push(ch);
            } else {
                //1. 当处理多位数时, 不能发现是一个数就立即入栈, 因为他可能是多位数
                //2. 在处理数, 需要向expression的表达式的index后再看一位, 如果是数就进行先不入栈，
                //   等到index的后一位是符号在入栈，这个期间moreNum是一直在积累数字的
                //3. 因此我们需要定义一个变量字符串，用于拼接
                moreNum += ch;

                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(moreNum));
                } else {
                    if (operStack.isOperation(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(moreNum));
                        //清空
                        moreNum = "";
                    }
                }
            }

            index++;
            if (index >= expression.length()) {
                break;
            }
        }

        while (!operStack.isEmpty()) {
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            int result = operStack.calculation(num2, num1, oper);
            numStack.push(result);
        }

        System.out.println(numStack.pop());

    }

}


class ArrayStackC {
    public int[] data;
    public int maxSize;
    public int topIndex = -1;

    public ArrayStackC(int maxSize) {
        data = new int[maxSize];
        this.maxSize = maxSize;
    }

    public boolean isFull() {
        return topIndex == maxSize - 1;
    }

    public boolean isEmpty() {
        return topIndex == -1;
    }

    public void push(int data) {
        if (isFull()) {
            System.out.println("栈已满");
            return;
        }

        topIndex++;
        this.data[topIndex] = data;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈是空的");
        }

        int value = data[topIndex];
        topIndex--;
        return value;
    }

    public void popAll() {
        if (isEmpty()) {
            throw new RuntimeException("栈是空的");
        }

        while (topIndex != -1) {
            System.out.println(data[topIndex]);
            topIndex--;
        }
    }

    public int lookStackTop() {
        return data[topIndex];
    }

    //设置运输符优先级，算数符号只有-，+，*，/
    public int priority(int operation) {
        if (operation == '*' || operation == '/') {
            return 1;
        } else if (operation == '-' || operation == '+') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是否是符号
    public boolean isOperation(char operation) {
        return operation == '/' || operation == '*' || operation == '-' || operation == '+';
    }

    public int calculation(int a, int b, int operation) {
        switch (operation) {
            case '-':
                return a - b;
            case '+':
                return a + b;
            case '/':
                return a / b;
            case '*':
                return a * b;
            default:
                throw new RuntimeException("运算符输入错误");
        }
    }
}
