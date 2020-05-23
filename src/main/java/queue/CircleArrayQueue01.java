package queue;

public class CircleArrayQueue01 {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(5);
        arrayQueue.addToQueue(1);
        arrayQueue.addToQueue(2);
        arrayQueue.addToQueue(3);
        arrayQueue.addToQueue(4);
        arrayQueue.addToQueue(5);
        arrayQueue.getFromQueue();
        arrayQueue.getFromQueue();
        arrayQueue.getFromQueue();
        arrayQueue.addToQueue(3);
        arrayQueue.addToQueue(5);
        arrayQueue.showAllData();
    }
}

class CircleArrayQueue {
    private int front;
    private int rear;
    private int[] data;
    private int maxSize;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize+1;
        data = new int[maxSize+1];
        this.front = 0;
        this.rear = 0;
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front ;
    }

    public boolean isEmpty(){
        return rear == front;
    }

    public void addToQueue(int data){
        if (isFull()){
            throw new RuntimeException("队列满了");
        }

        this.data[rear] = data;
        rear = (rear+1) % maxSize;
    }

    public int getFromQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }

        int value = data[front];
        front = (front+1)%maxSize;
        return value;
    }

    public void showAllData(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i=front; i != rear; i = (i+1)%maxSize){
            System.out.printf("%d\n",data[i]);
        }
    }

}
