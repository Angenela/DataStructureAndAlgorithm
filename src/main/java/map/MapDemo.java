package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MapDemo {
    public static void main(String[] args) {
        Map map = new Map(5);
        map.addVertex("A");
        map.addVertex("B");
        map.addVertex("C");
        map.addVertex("D");
        map.addVertex("E");
        map.addEdge(0, 1, 1);
        map.addEdge(0, 2, 1);
        map.addEdge(2, 1, 1);
        map.addEdge(1, 3, 1);
        map.addEdge(1, 4, 1);
          
        map.BFS(map.isVisited, 0);
    }
}

class Map {
    public ArrayList<String> vertexList;        // 存储顶点
    public int edges[][];       // 邻接矩阵，比如vertexList的索引0和1对应的顶点，对应的的边就是edges[0][1]或edges[1][0]
    public int edgeCount;       // 边的个数
    public boolean isVisited[];     // 遍历时，判断是否被遍历过

    public Map(int count) {
        vertexList = new ArrayList<String>();
        edges = new int[count][count];
        edgeCount = 0;
        isVisited = new boolean[count];
    }

    // 广度优先遍历
    public void BFS(boolean isVisited[], int start) {
        isVisited = new boolean[5];
        System.out.print(vertexList.get(start));
        LinkedList<Integer> queue = new LinkedList<Integer>();      // 栈
        isVisited[start] = true;
        queue.add(start);
        int next;
        while (!queue.isEmpty()) {
            start = queue.removeFirst();
            next = getFirstAdjacency(start);
            while (next != -1) {
                if (!isVisited[next]) {      // 判断是否被访问过
                    System.out.print("--->" + vertexList.get(next));
                    isVisited[next] = true;
                    queue.add(next);
                }
                next = getNextAdjacency(start, next);
            }
        }
    }

    //  深度优先遍历
    public void DFS(boolean isVisited[], int start) {
        isVisited = new boolean[5];
        System.out.print(vertexList.get(start));
        isVisited[start] = true;
        int next = getFirstAdjacency(start);        // 获取第一个邻接结点
        while (next != -1) {
            if (!isVisited[next]) {      // 判断是否被访问过
                System.out.print("--->");
                DFS(isVisited, next);
            }
            next = getNextAdjacency(start, next);    // 到这的是否next肯定是被访问了的，那就可以获取下一个邻接结点
        }
    }

    // 当 index 当前的邻接结点不存在或已被访问时，获取下一个邻接节点
    public int getNextAdjacency(int index, int now) {
        for (int i = now + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;      // 没有找到
    }

    // 获取第一个邻接节点
    public int getFirstAdjacency(int index) {
        return getNextAdjacency(index, -1);     // 代码的复用，也可为下面的
//        for (int i = 0; i < vertexList.size(); i++){
//            if (edges[index][i] > 0){
//                return i;
//            }
//        }
//        return -1;      // 没有找到
    }

    public void showEdges() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
        System.out.println();
    }

    // 获取两个索引的边的权值
    public int weight(int v1, int v2) {
        return edges[v1][v2];
    }

    // 根据vertexList的索引获取顶点
    public String getVertex(int index) {
        return vertexList.get(index);
    }

    public int vertexCount() {
        return vertexList.size();
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void addEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeCount++;
    }
}
