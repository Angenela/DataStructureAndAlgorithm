package tree;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int array[] = new int[]{10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();

        for (int i = 0; i < array.length; i++) {
            avlTree.add(new AVLNode(array[i]));
        }

        avlTree.midOrder();
        System.out.println("-------------");
        System.out.println("AVL树的高度：" + avlTree.root.height());
        System.out.println("左子树的高度：" + avlTree.root.leftHeight());
        System.out.println("右子树的高度：" + avlTree.root.rightHeight());
    }
}

class AVLTree {
    AVLNode root;

    public void deleteNode(int value) {
        if (root == null) {
            return;
        } else {
            // 获取目标节点
            AVLNode target = root.search(value);
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
            AVLNode parent = root.searchParent(value);

            // 判断是否为叶子节点
            if (target.left == null && target.right == null) {
                // 判断此叶子节点在parent节点的左还是右
                if (parent.left != null && parent.left.value == target.value) {  // target在parent的left
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else if (target.left != null && target.right != null) {      // target有两个子节点
                AVLNode temp = target.right;
                int tempData;
                while (temp.left != null) {      // 找到target右子树中最小的节点
                    temp = temp.left;
                }
                tempData = temp.value;     // 保存最小节点的值
                deleteNode(tempData);       // 删除最小节点
                target.value = tempData;        // 替换最小节点和target的值
            } else {     // target只有一个子节点
                // 此时为root只有一个子节点
                if (parent == null) {
                    if (target.left != null) {
                        target.value = target.left.value;
                        target.left = null;
                    } else {
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

    public AVLNode search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    public AVLNode searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    public void add(AVLNode node) {
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

class AVLNode {
    int value;
    AVLNode left;
    AVLNode right;

    // 以node为根节点进行右旋转
    public void rightRotate() {
        // 创建新节点，value和this一样
        AVLNode newNode = new AVLNode(value);
        // 把新节点的右子树设置了当前节点的右子树
        newNode.right = right;
        // 把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        // 把当前节点的值换为左子节点的值
        value = left.value;
        // 把当前节点的左子树设置成左子树的左子树
        left = left.left;
        // 把当前节点的右子树设置为新节点
        right = newNode;
    }

    // 以node为根节点进行左旋转
    public void leftRotate() {
        // 1. 创建新节点，value和this一样
        AVLNode newNode = new AVLNode(value);
        // 2. newNode的左子树和this一样
        newNode.left = left;
        // 3. newNode的右子树为this的右子树的左子树
        newNode.right = right.left;
        // 4. this的value等于this的右子树的value
        value = right.value;
        // 5. this的右子树为this右子树的右子树
        right = right.right;
        // 6. this的左子树为newNode
        left = newNode;
    }

    public AVLNode(int value) {
        this.value = value;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 返回以当前节点为根节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 通过节点的value查找父节点
    public AVLNode searchParent(int value) {
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
    public AVLNode search(int value) {
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

    public void add(AVLNode node) {
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

        // 如果右子树的高度 - 左子树的高度 > 1，进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()){
                right.rightRotate();
            }
            leftRotate();
            return;
        }

        // 如果左子树的高度 - 右子树的高度 > 1，进行右旋转
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()){
                left.leftRotate();
            }
            rightRotate();
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
        return "AVLNode{" +
                "value=" + value +
                '}';
    }
}