package linkedList;

public class JosephuDemo {
    public static void main(String[] args) {
        JosephuLinkedList josephuLinkedList = new JosephuLinkedList();
        josephuLinkedList.JosephuNumber(10,125,20);
    }
}

class JosephuNode {
    public JosephuNode next;
    public int id;

    public JosephuNode(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JosephuNode{" +
                "id=" + id +
                '}';
    }
}

class JosephuLinkedList {
    public JosephuNode headNode;
    public JosephuNode lastNode;

    public void add(int mount) {
        for (int i=1; i<=mount; i++){
            if (headNode == null){  //约瑟夫环为空
                headNode = new JosephuNode(1);
                headNode.next = headNode;   //让唯一的节点指向自己
                lastNode = headNode;
                continue;
            }
            JosephuNode josephuNode = new JosephuNode(i);
            lastNode.next = josephuNode;
            josephuNode.next = headNode;
            lastNode = josephuNode;
        }
    }

    public void showAll() {

        JosephuNode curNode = headNode;

        while (true){
            System.out.println(curNode);
            curNode = curNode.next;
            if (curNode == headNode){
                break;
            }
        }
    }

    /**
     *
     * @param startNum 从第几个节点开始
     * @param mount 一共有几个节点
     * @param interval 数几下
     */
    public void JosephuNumber(int startNum,int mount,int interval){

        add(mount);

        for (int i=1; i<startNum; i++){    //移动到指定的节点开始计算
            headNode = headNode.next;
            lastNode = lastNode.next;
        }

        while (headNode.next != headNode){
            for (int i=1; i<interval; i++){  //因为是从head指向的节点开始计数的，所以从1开始
                headNode = headNode.next;
                lastNode = lastNode.next;
            }
            headNode = headNode.next;
            System.out.println(lastNode.next.id);
            lastNode.next = headNode;
        }
        System.out.println("最后的 id  =  "+headNode.id);  //这时候只剩下最后一个节点
        headNode = null;    //清除最后一个节点
    }
}
