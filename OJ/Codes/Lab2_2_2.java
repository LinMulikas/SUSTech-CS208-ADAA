import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Lab2_2_2 {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        int T = Integer.parseInt(line[0]);
        ArrayList<Integer> result = new ArrayList<>();

        for(int i = 1; i <= T; i++){
            line = in.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            DualGraph graph = new DualGraph(n);

            for(int j = 1; j <= m; j++){
                line = in.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int b = Integer.parseInt(line[1]);
                graph.connect(a, b);
            }

            result.add(graph.searchMiniCircle());
        }

        for(int res : result){
            System.out.println(res);
        }
    }
}

class DualGraph{
    int size;
    Node[] nodesOut;
    Node[] nodesIn;

    DualGraph(int size){
        this.size = size;
        this.nodesOut = new Node[size + 1];
        this.nodesIn = new Node[size + 1];

        for(int i = 1; i <= size; i ++){
            this.nodesIn[i] = new Node(i);
            this.nodesOut[i] = new Node(i);
        }
    }


    public int searchMiniCircle(){
        int result = -1;
        ArrayDeque<Node> nodesToSearch = new ArrayDeque<>();
        for(int i = 1; i <= this.size; i++){
            if(nodesOut[i].degree > 1){
                nodesToSearch.add(nodesOut[i]);
            }
            if(nodesIn[i].degree > 1){
                nodesToSearch.add(nodesIn[i]);
            }
        }

        while(!nodesToSearch.isEmpty()){
            Node node = nodesToSearch.pollFirst();
            int tryAnswer = this.BFSLength(node);
            if(tryAnswer == -1) continue;
            if(result == -1) result = tryAnswer;
            result = Math.min(tryAnswer, result);
        }
        return result;
    }

    private int BFSLength(Node s){
        for(int i = 1; i <= size; i++){
            this.nodesIn[i].depth = -1;
            this.nodesOut[i].depth = -1;
        }

        s.depth = 0;
        ArrayDeque<Node> toSearch = new ArrayDeque<>();
        ArrayDeque<Node> next = new ArrayDeque<>();
        toSearch.add(s);
    
        while(!toSearch.isEmpty()){
            while(!toSearch.isEmpty()){
                Node node = toSearch.pollFirst();
                for(Node child : node.children){
                    if(child.depth == -1){
                        child.depth = node.depth + 1;
                        next.add(child);
                    }else{
                        if(child.depth < node.depth) continue;
                        return child.depth + node.depth + 1;
                    }
                }
            }
            toSearch.addAll(next);
            next.clear();
        }
        return -1;
    }


    public void connect(int out_id, int in_id){
        Node out = nodesOut[out_id];
        Node in = nodesIn[in_id];

        out.children.add(in);
        out.degree ++;
        in.children.add(out);
        in.degree ++;
    }

    private class Node implements Comparable<Node>{
        int id;
        int degree = 0;
        int depth = -1;
        ArrayList<Node> children = new ArrayList<>();

        Node(int x){
            this.id = x;
        }

        public int compareTo(Node o) {
            return this.degree - o.degree;
        }

    }
}