package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        treeNode1.leftNode = treeNode2;
        treeNode1.rightNode = treeNode3;
        treeNode2.leftNode = treeNode4;
        treeNode2.rightNode = treeNode5;

        BinaryTree binaryTree = new BinaryTree(treeNode1);
        System.out.println(binaryTree.preSearch(6));
    }
}

class BinaryTree {
    public TreeNode rootNode;

    public BinaryTree(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public boolean isEmpty() {
        return rootNode == null;
    }

    public TreeNode preSearch(int data) {
        if (rootNode != null) {
            return rootNode.preSearch(data);
        } else {
            return null;
        }
    }

    public void preOrder() {
        if (rootNode != null) {
            rootNode.preOrder();
        } else {
            System.out.println("rootNode为空");
        }
    }
}

class TreeNode {
    public TreeNode leftNode;
    public TreeNode rightNode;
    public int data;

    public TreeNode(int data) {
        this.data = data;
    }

    // 前序查找, 其它序的查找调换一下顺序就好
    public TreeNode preSearch(int data) {
        if (this.data == data) {
            return this;
        }

        TreeNode temp = null;
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
