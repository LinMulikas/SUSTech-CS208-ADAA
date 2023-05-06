import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

public class OJ_3_2 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer tk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        tk.nextToken();
        int T = (int) tk.nval;

        for (int i = 1; i <= T; i++) {
            tk.nextToken();
            int vertex = (int) tk.nval;
            tk.nextToken();
            int edges = (int) tk.nval;

            // 建立图
            DAGraph graph = new DAGraph(vertex, edges);

            // 记录原图的边
            for (int cnt_edge = 1; cnt_edge <= edges; cnt_edge++) {
                tk.nextToken();
                int a = (int) tk.nval;
                tk.nextToken();
                int b = (int) tk.nval;

                graph.edges[cnt_edge][0] = a;
                graph.edges[cnt_edge][1] = b;
                graph.nodes[b].fathersID.add(a);
            }

            // 反向寻路并尝试建立图
            graph.solve();
        }
    }

    static class DAGraph {
        int size;
        boolean normal;
        Node[] nodes;
        HashMap<Integer, Node> findNode = new HashMap<>();
        int[][] edges;
        UF uf;
        public UF clsUF;

        DAGraph(int size, int edge) {
            this.nodes = new Node[size + 1];
            this.size = size;
            this.edges = new int[edge + 1][2];
            this.uf = new UF(size);

            for (int i = 1; i <= size; i++) {
                this.nodes[i] = new Node(i);
            }
        }

        void connect(int x, int y) {
            this.nodes[x].children.add(this.nodes[y]);
        }

        void solve() {
            ArrayDeque<Node> current = new ArrayDeque<>();
            ArrayDeque<Node> next = new ArrayDeque<>();

            current.add(this.nodes[size]);

            while (!current.isEmpty()) {
                while (!current.isEmpty()) {
                    Node tmp = current.pollFirst();
                    for (int x : tmp.fathersID) {
                        next.addLast(this.nodes[x]);
                    }

                    this.uf.unionAll(tmp.fathersID);
                }
                current.addAll(next);
                next.clear();
            }

            this.clsUF = new UF(uf.vertex);

            next = new ArrayDeque<>();

            ArrayDeque<Node> nodesQueue = new ArrayDeque<>();

            HashSet<Integer> fatherCls = new HashSet<>();
            HashSet<Integer> fatherNodes = new HashSet<>();

            nodesQueue.add(this.nodes[this.uf.find(size)]);

            while (!nodesQueue.isEmpty()) {
                while (!nodesQueue.isEmpty()) {
                    Node tmpNode = nodesQueue.pollFirst();

                    for (int x : tmpNode.fathersID) {
                        fatherNodes.add(x);
                    }
                }

                for(int x : fatherNodes){
                    fatherCls.add(this.uf.find(x));
                    nodesQueue.add(this.nodes[x]);
                }

                if(!this.clsUF.unionAll(new ArrayDeque<>(fatherCls))){
                    System.out.printf("No\n");
                    return;
                }

                fatherCls.clear();
                fatherNodes.clear();
            }

            DAGraph.topoSort(this);

            if (this.normal) {
                System.out.println("Yes");
                StringBuilder str = new StringBuilder();

                for (int i = 1; i <= this.size; i++) {
                    str.append(this.getValue(i));
                }

                str.append("\n");
                System.out.printf(str.toString());
            } else {
            }
        }

        public static boolean topoSort(DAGraph graph) {
            Node root = graph.nodes[1];
            root.value = 1;
            root.distance = 0;
            root.visit = true;
            ArrayDeque<Node> queue = new ArrayDeque<>();
            queue.add(root);
            ArrayDeque<Node> next = new ArrayDeque<>();

            while (!queue.isEmpty()) {
                while (!queue.isEmpty()) {
                    Node tmp = queue.pollFirst();
                    if (tmp.children.isEmpty())
                        return true;
                    for (Node child : tmp.children) {
                    
                        child.visit = true;
                        child.distance = Math.max(child.distance, tmp.distance + 1);
                    }
                }
                queue.addAll(next);
                next.clear();
            }
            return true;
        }

        int getValue(int id) {
            int nodeNewID = this.uf.find(id);
            int fatherNewID = this.uf.find(uf.find(id));
            return this.nodes[nodeNewID].distance - this.nodes[fatherNewID].distance;
        }

    }

    static class simpleNode{

    }

    static class Node {
        int id;
        int value;
        int distance = 0;
        boolean visit = false;

        HashSet<Node> children = new HashSet<>();
        ArrayDeque<Integer> fathersID = new ArrayDeque<>();

        Node(int id) {
            this.id = id;
        }
    }

    static class UF {
        int vertex;
        int size;
        int[] parent;
        int[] weight;

        UF(int size) {
            this.vertex = size;
            this.size = size;
            this.parent = new int[size + 1];
            this.weight = new int[size + 1];
            for (int i = 1; i <= size; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        void reshape() {
            for (int i = 1; i <= size; i++) {
                this.find(i);
            }
        }

        boolean unionAndCheck(ArrayDeque<Integer> fathers){
            if(fathers.size() == 1) return true;
            int a = fathers.peek();

            for(int x : fathers){
                if(a == x) continue;
                if(this.isConnected(a, x)){
                    return false;
                }else{
                    this.union(a, x);
                }
            }
            return true;
        }

        boolean unionAll(ArrayDeque<Integer> fathers) {
            if (fathers.isEmpty() || fathers.size() == 1)
                return true;
                
            int a = fathers.peek();
            
            for(int x : fathers){
                if(a == x) continue;
                if(this.isConnected(a, x)){
                    return false;
                }
                this.union(a, x);
                a = x;
            }
            return true;
        }

        void union(int a, int b) {
            int rootA = this.find(a);
            int rootB = this.find(b);

            if (this.weight[rootA] > this.weight[rootB]) {
                parent[rootB] = parent[rootA];
                weight[rootA] += weight[rootB];
            } else {
                parent[rootA] = parent[rootB];
                weight[rootB] += weight[rootA];
            }
            size--;
        }

        boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }

        int find(int id) {
            while (parent[id] != id) {
                parent[id] = parent[parent[id]];
                id = parent[id];
            }
            return id;
        }
    }
}
