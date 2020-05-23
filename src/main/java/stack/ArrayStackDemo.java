package stack;

public class ArrayStackDemo {
    public static void main(String[] args) {

        ArrayStackC arrayStack = new ArrayStackC(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);
        arrayStack.popAll();
        arrayStack.pop();

    }
}

class ArrayStack{
    public int[] data;
    public int maxSize;
    public int topIndex = -1;

    public ArrayStack(int maxSize){
        data = new int[maxSize];
        this.maxSize = maxSize;
    }

    public boolean isFull(){
        return topIndex == maxSize - 1;
    }

    public boolean isEmpty(){
        return topIndex == -1;
    }

    public void push(int data){
        if (isFull()){
            System.out.println("栈已满");
            return;
        }

        topIndex ++;
        this.data[topIndex] = data;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈是空的");
        }

        int value = data[topIndex];
        topIndex--;
        return value;
    }

    public void popAll(){
        if (isEmpty()){
            throw new RuntimeException("栈是空的");
        }

        while (topIndex != -1){
            System.out.println(data[topIndex]);
            topIndex--;
        }
    }
}

