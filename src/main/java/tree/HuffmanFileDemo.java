package tree;

import java.io.*;
import java.util.List;

import static tree.HuffmanCompressionDemo.*;

public class HuffmanFileDemo {
    public static void main(String[] args) {
        // 赫夫曼压缩步骤：

        File image = new File("C:\\Users\\安基尼拉\\Pictures\\Saved Pictures\\白毛.jpg");

        // 1. 获字节数组
        byte[] bytes = null;
        try {
            FileInputStream fis = new FileInputStream(image);
            System.out.println("原来的长度："+fis.available()+"字节");
            bytes = new byte[fis.available()];
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. 获取字符对应节点数组，节点的数组有字符对应的ascii码和权值（即出现的次数）
        List<HuffmanNode> nodes = getNodes(bytes);

        // 3. 根据节点数组创建赫夫曼树
        HuffmanNode root = getTree(nodes);

        // 4. 根据赫夫曼树获取赫夫曼编码
        createHuffmanCodes(root, new StringBuilder());

        // 5. 根据赫夫曼编码获取编码
        byte[] zip = zip(bytes);
        System.out.println("压缩后的长度："+zip.length+"字节");

        // 6. 解码（验证是否正确）
        File file = new File("D:\\MyProject\\image\\白毛.zip");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(zip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
