package linkedList;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList(new DoubleNode(0, ""));
        doubleLinkedList.add(new DoubleNode(1,"Angenela1"));
        doubleLinkedList.add(new DoubleNode(2,"Angenela2"));
        doubleLinkedList.add(new DoubleNode(3,"Angenela3"));
        doubleLinkedList.add(new DoubleNode(4,"Angenela4"));
//        doubleLinkedList.showAll();
//        System.out.println(doubleLinkedList.getById(4));
        doubleLinkedList.deleteById(5);
        doubleLinkedList.showAll();
    }
}

class DoubleNode{
    public int id;
    public String data;
    public DoubleNode pre;
    public DoubleNode next;


    public DoubleNode(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DoubleNode{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}

class DoubleLinkedList{
    public DoubleNode headNode;
    public DoubleNode lastNode;

    public DoubleLinkedList(DoubleNode headNode) {
        this.headNode = headNode;
        this.lastNode = headNode;
    }

    public boolean isEmpty(){
        return headNode.next == null;
    }

    public void add(DoubleNode node){
        lastNode.next = node;
        node.pre = lastNode;
        lastNode = node;
    }

    public DoubleNode getById(int id){
        DoubleNode cur = headNode.next;

        while (cur!=null&&cur.id!=id){
            cur = cur.next;
        }

        return cur;
    }

    public void deleteById(Integer id){
        DoubleNode node = getById(id);

        if (node == null){
            return;
        }

        if (node.next == null){
            node.pre.next = null;
            return;
        }

        node.pre.next = node.next;
        node.next.pre = node.pre;

        return;
    }

    public void showAll(){
        DoubleNode cur = headNode.next;

        while (cur!=null){
            System.out.println(cur);
            cur = cur.next;
        }
    }
}


