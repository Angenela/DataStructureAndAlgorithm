package tree;

import java.util.*;

public class HuffmanCompressionDemo {
    public static void main(String[] args) {
        // 赫夫曼压缩步骤：

        String str = "i like like like java do you like a java";

        // 1. 获取字符串的字节数组
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length);

        // 2. 获取字符对应节点数组，节点的数组有字符对应的ascii码和权值（即出现的次数）
        List<HuffmanNode> nodes = getNodes(bytes);

        // 3. 根据节点数组创建赫夫曼树
        HuffmanNode root = getTree(nodes);

        // 4. 根据赫夫曼树获取赫夫曼编码
        createHuffmanCodes(root, new StringBuilder());

        // 5. 根据赫夫曼编码获取编码
        byte[] zip = zip(bytes);
        System.out.println(zip.length);

        // 5. 解码（验证是否正确）
        byte[] decode = decode(huffmanCodes, zip);
    }

    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串，形式1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<Byte>();
        //i可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //1010100010111...
                //递增的取出key
                String key = stringBuilder.substring(i, i + count); //i不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;

                }
            }
            list.add(b);
            i += count;     //i直接移动到count
        }

        byte[] decode = new byte[list.size()];
        for (int i = 0; i < decode.length; i++){
            decode[i] = list.get(i);
        }

        return decode;
    }

    /**
     * 将一个byte转成一一个二进制的字符串
     *
     * @param b    传入的byte
     * @param flag 标志是否需要补高位如果是true , 表示需要补高位，如果是false表示不补
     * @return是该b对应的二进制的字符串，(注意是按补码返回)
     */
    private static String byteToBitString(boolean flag, byte b) {
        //使用变量保存b
        int temp = b;//将b转成int
        //如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; //按位与 256 1 0000 0000 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    public static byte[] zip(byte bytes[]) {
        StringBuilder str = new StringBuilder();
        for (byte aByte : bytes) {
            str.append(huffmanCodes.get(aByte));
        }
        // 将二进制字符串转为ascii码
        int len = str.length();
        if (len % 8 != 0) {
            len = len / 8 + 1;
        }

        byte codes[] = new byte[len];
        int index = 0;
        String temp;
        for (int i = 0; i < str.length(); i += 8) {
            if (i + 8 > str.length()) {
                temp = str.substring(i);
            } else {
                temp = str.substring(i, i + 8);
            }
            codes[index++] = (byte) Integer.parseInt(temp, 2);
        }

        return codes;
    }

    // 获取赫夫曼编码
    public static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    // 因为拼接操作比较多，用stringbuilder比较合适

    /**
     * 功能：将从root到赫夫曼树的叶子节点（即字符对应的节点）的路径存放到huffmanCodes
     * 路径：向左为0，向右为1
     *
     * @param node
     * @param str
     */
    public static void createHuffmanCodes(HuffmanNode node, StringBuilder str) {
        StringBuilder strTemp = new StringBuilder(str);
        if (node != null) {
            // 这个节点不是叶子节点
            if (node.data == null) {
                createHuffmanCodes(node.left, strTemp.append("0"));
                // strtemp 在上面加了个0， 清空一下
                strTemp = str;
                createHuffmanCodes(node.right, strTemp.append("1"));
            } else {
                huffmanCodes.put(node.data, str.toString());
            }
        }
    }

    // 前序遍历
    public static void preOrder(HuffmanNode root) {
        System.out.println(root);
        if (root.left != null) {
            preOrder(root.left);
        }
        if (root.right != null) {
            preOrder(root.right);
        }
    }

    // 创建赫夫曼树
    public static HuffmanNode getTree(List<HuffmanNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            HuffmanNode node1 = nodes.get(0);
            HuffmanNode node2 = nodes.get(1);

            HuffmanNode parent = new HuffmanNode(null, node1.weight + node2.weight);

            parent.left = node1;
            parent.right = node2;

            nodes.remove(node1);
            nodes.remove(node2);

            nodes.add(parent);
        }

        return nodes.get(0);
    }

    // 创建节点的集合，用于构建赫夫曼树
    public static List<HuffmanNode> getNodes(byte bytes[]) {
        List<HuffmanNode> nodes = new ArrayList<HuffmanNode>();
        // 统计各个字符的权值
        Map<Byte, Integer> counts = new HashMap<Byte, Integer>();
        for (byte aByte : bytes) {
            Integer count = counts.get(aByte);
            if (count == null) {
                counts.put(aByte, 1);
            } else {
                counts.put(aByte, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }
}

class HuffmanNode implements Comparable<HuffmanNode> {
    Byte data;
    int weight;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return weight - o.weight;
    }
}
