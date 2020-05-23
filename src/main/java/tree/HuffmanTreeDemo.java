package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int array[] = new int[]{1, 3, 6, 13, 7, 8, 29};
        Node node = ArrayToHuffman(array);
        preOrder(node);
    }

    public static Node ArrayToHuffman(int[] array) {
        List<Node> nodes = new ArrayList<Node>();
        for (int data : array) {
            nodes.add(new Node(data));
        }

        while (nodes.size() > 1) {
            // 1. 排序
            Collections.sort(nodes);

            // 2. 取出最小的两个节点
            Node nodeA = nodes.get(0);
            Node nodeB = nodes.get(1);

            // 3. 创建一个新的二叉树
            Node parent = new Node(nodeA.value + nodeB.value);
            parent.left = nodeA;
            parent.right = nodeB;

            // 4. 去除最小的两个节点
            nodes.remove(nodeA);
            nodes.remove(nodeB);

            // 5.添加
            nodes.add(parent);
        }

        return nodes.get(0);
    }

    public static void preOrder(Node node){
        System.out.println(node);
        if (node.left !=null){
            preOrder(node.left);
        }

        if (node.right !=null){
            preOrder(node.right);
        }
    }

}

class Node implements Comparable<Node> {
    public int value;     // 权值
       public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value ;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
