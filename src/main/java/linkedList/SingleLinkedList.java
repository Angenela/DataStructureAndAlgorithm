package linkedList;

import java.util.Stack;

public class SingleLinkedList {
    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(new Node(1,"Angenela1"));
        linkedList.add(new Node(2,"Angenela2"));
        linkedList.add(new Node(3,"Angenela3"));
        linkedList.add(new Node(4,"Angenela4"));
        linkedList.showAllNode();
        SingleLinkedList.reverse(linkedList);
        System.out.println("---------------");
//        linkedList.showAllNode();
        SingleLinkedList.reservePrint(linkedList.headNode);

    }


    public static void reservePrint(Node headNode){
        if (headNode.nextNode == null){
            return;
        }

        Stack<Node> stack = new Stack<Node>();
        Node cur = headNode.nextNode;

        while (cur!=null){
            stack.push(cur);
            cur = cur.nextNode;
        }

        for (Node node : stack) {
            System.out.println(node);
        }
    }


    public static void reverse(LinkedList linkedList) {
        Node headNode = linkedList.headNode;
        if (headNode.nextNode == null || headNode.nextNode.nextNode == null){
            return;
        }

        Node next;
        Node cur = headNode.nextNode;
        Node reverseNodeHead = new Node(0,"");

        while (cur!=null){
            next = cur.nextNode;
            cur.nextNode = reverseNodeHead.nextNode;
            reverseNodeHead.nextNode = cur;
            cur = next;
        }

        linkedList.headNode = reverseNodeHead;
    }

}

class LinkedList{
    public int size = 0;
    public Node headNode = new Node(0,"");
    public Node lastNode = headNode;

    public void add(Node node){
        lastNode.nextNode = node;
        lastNode = node;
        size++;
    }

    public void showAllNode(){
        if (headNode.nextNode == null){
            System.out.println("链表为空！");
            return;
        }

        Node temNode = headNode.nextNode;
        while (temNode!=null){
            System.out.println(temNode);
            temNode = temNode.nextNode;
        }
    }
}

class Node{
    public int id;
    public String data;
    public Node nextNode = null;

    public Node(int id, String data) {
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
