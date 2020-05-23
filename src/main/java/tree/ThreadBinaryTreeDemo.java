package tree;

public class ThreadBinaryTreeDemo {
    public static void main(String[] args) {

    }
}

class ThreadBinaryTree {
    public ThreadNode rootNode;
    public ThreadNode pre;

    public ThreadBinaryTree(ThreadNode rootNode) {
        this.rootNode = rootNode;
    }

    // 中序线索化当前节点
    public void toThread(ThreadNode node) {
        if (node == null) {
            return;
        }

        // 线索化左节点
        toThread(node.leftNode);

        // 线索化当前节点
        if (node.leftNode == null) {
            node.leftNode = pre;
            // 1 为前驱节点， 0 为左子树
            node.leftType = 1;
        }

        // 线索化右节点，并不是在同一时间线索化左右节点
        // 使用递归的回溯，回溯到后继节点再线索化前驱节点的右节点
        if (pre != null && pre.rightNode == null){
            pre.rightNode = node;
            pre.rightType = 1;
        }

        // 保存当前节点，再线索化右节点时有用
        pre = node;

        toThread(node.rightNode);
    }

    public boolean isEmpty() {
        return rootNode == null;
    }
}

class ThreadNode {
    public ThreadNode leftNode;
    public ThreadNode rightNode;
    public int data;
    public int leftType = 0;
    public int rightType = 0;

    public ThreadNode(int data) {
        this.data = data;
    }

    // 前序查找, 其它序的查找调换一下顺序就好
    public ThreadNode preSearch(int data) {
        if (this.data == data) {
            return this;
        }

        ThreadNode temp = null;
        // 不能直接return结果，因为总会有结果，是null或目标节点
        if (this.leftNode != null) {
            temp = this.leftNode.preSearch(data);
        }

        // 说明上面的递归找到了
        if (temp != null) {
            return temp;
        }

        if (this.rightNode != null) {
            temp = this.rightNode.preSearch(data);
        }

        return temp;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    // 中序遍历
    public void midOrder() {
        if (this.leftNode != null) {
            this.leftNode.midOrder();
        }
        System.out.println(this);
        if (this.rightNode != null) {
            this.rightNode.midOrder();
        }
    }

    // 后序遍历
    public void RearOrder() {
        if (this.leftNode != null) {
            this.leftNode.RearOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.RearOrder();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "data=" + data +
                '}';
    }

}
