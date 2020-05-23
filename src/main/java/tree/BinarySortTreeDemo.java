package tree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int array[] = new int[]{7, 3, 10, 1, 5, 9, 12, 2};
        BSTTree tree = new BSTTree();
        for (int i = 0; i < array.length; i++) {
            tree.add(new BSTNode(array[i]));
        }

        tree.midOrder();
        tree.deleteNode(2);
        tree.deleteNode(5);
        tree.deleteNode(9);
        tree.deleteNode(12);
        tree.deleteNode(7);
        tree.deleteNode(3);
        tree.deleteNode(10);
        tree.deleteNode(1);
        System.out.println("------------------");
        tree.midOrder();
    }
}

class BSTTree {
    BSTNode root;

    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            // 获取目标节点
            BSTNode target = root.search(value);
            // 如果目标节点为空，直接退出
            if (target == null) {
                return;
            }
            // 如果树只有一个节点，即root节点，那么查找的就是root节点，直接设置为null
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 获取target节点的父节点
            BSTNode parent = root.searchParent(value);

            // 判断是否为叶子节点
            if (target.left == null && target.right == null) {
                // 判断此叶子节点在parent节点的左还是右
                if (parent.left != null && parent.left.value == target.value) {  // target在parent的left
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (target.left != null && target.right != null) {      // target有两个子节点
                BSTNode temp = target.right;
                int tempData;
                while (temp.left != null) {      // 找到target右子树中最小的节点
                    temp = temp.left;
                }
                tempData = temp.value;     // 保存最小节点的值
                deleteNode(tempData);       // 删除最小节点
                target.value = tempData;        // 替换最小节点和target的值
            } else {     // target只有一个子节点
                // 此时为root只有一个子节点
                if (parent == null){
                    if (target.left != null){
                        target.value = target.left.value;
                        target.left = null;
                    }else {
                        target.value = target.right.value;
                        target.right = null;
                    }
                    return;
                }
                // 这是root子节点只有一个节点
                if (target.left != null) {
                    if (parent.left.value == target.value) {
                        parent.left = target.left;
                    } else {
                        parent.right = target.left;
                    }
                } else {
                    if (parent.left.value == target.value) {
                        parent.left = target.right;
                    } else {
                        parent.right = target.right;
                    }
                }
            }
        }
    }

    public BSTNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public BSTNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void add(BSTNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            root.add(node);
        }
    }

    public void midOrder() {
        if (root == null) {
            System.out.println("root为空");
        } else {
            root.midOrder();
        }
    }
}

class BSTNode {
    int value;
    BSTNode left;
    BSTNode right;

    public BSTNode(int value) {
        this.value = value;
    }

    // 通过节点的value查找父节点
    public BSTNode searchParent(int value) {
        // 判断当前节点是否是父节点
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        }
        if (this.value > value && this.left != null) {   // 向左递归
            return this.left.searchParent(value);
        } else if (this.value <= value && this.right != null) {
            // 之所以加等号是因为增加节点时规定：和root节点的value相同的节点丢到右边去
            // 所以加等号就是期望可以在右边找到和root的value一样的节点，不过尽量不要添加和root的value一样的节点
            return this.right.searchParent(value);
        } else {     // 上面的条件不满足，说明没有父节点
            return null;
        }
    }

    // 查找节点
    public BSTNode search(int value) {
        if (this.value == value) {
            return this;
        }
        if (this.value > value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    public void add(BSTNode node) {
        if (node == null) {
            return;
        }

        // 比较node和当前节点
        if (this.value > node.value) {
            // 如果this.left为空，直接挂到上面
            if (this.left == null) {
                this.left = node;
                return;
            } else {
                // 向左递归找到空的节点
                this.left.add(node);
            }
        } else { // 和上面的差不多
            if (this.right == null) {
                this.right = node;
                return;
            } else {
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void midOrder() {
        if (this.left != null) {
            this.left.midOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.midOrder();
        }
    }

    @Override
    public String toString() {
        return "BSTNode{" +
                "value=" + value +
                '}';
    }
}
