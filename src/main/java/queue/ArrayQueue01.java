package queue;

public class ArrayQueue01 {
    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(5);
        arrayQueue.addToQueue(1);
        arrayQueue.addToQueue(2);
        arrayQueue.addToQueue(3);
        arrayQueue.showAllData();

    }
}

class ArrayQueue {
    private int front;
    private int rear;
    private int[] data;
    private int maxsize;

    public ArrayQueue(int maxSize) {
        this.maxsize = maxSize;
        data = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    public boolean isFull() {
        return rear == maxsize - 1;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addToQueue(int data){
        if (isFull()){
            throw new RuntimeException("队列满了");
        }

        rear++;
        this.data[rear] = data;
    }

    public int getFromQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }

        front++;
        return data[front];
    }

    public void showAllData(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i=front+1; i <= rear; i++){
            System.out.printf("%d\n",data[i]);
        }
    }

}
