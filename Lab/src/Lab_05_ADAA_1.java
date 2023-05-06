import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab_05_ADAA_1 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");
        int N_nodes = Integer.parseInt(line[0]);
        int N_edges = Integer.parseInt(line[1]);
        myGraph graph = new myGraph(N_nodes);

        for(int i = 1; i <= N_nodes; i++){
            graph.addNode(i);
        }

        for(int i = 0; i < N_edges; i++){
            line = in.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            graph.connect(a, b);
        }
    

        ArrayList<Integer> result =  graph.topoSort();
        for(int id : result){
            System.out.println(id);
        }
    }

}

class myGraph{

    class Node implements Comparable<Node>{
        boolean visited = false;
        int id;
        int degree = 0;
        ArrayList<Node> children = new ArrayList<>();;
    
        Node(int x){
            this.id = x;
        }
    
        public void setNext(Node that){
            this.children.add(that);
            that.addDegree();
        }

        void addDegree(){
            for(Node node : children){
                node.degree++;
            }
        }

        void delChildrenDegree(){
            for(Node node : children){
                node.degree--;
            }
        }


        @Override
        public int compareTo(Node o) {
            return this.id - o.id;
        }
    }

    ArrayList<Node> nodes = new ArrayList<>();

    myGraph(int size){
        this.nodes = new ArrayList<>();
        this.nodes.add(new Node(0));
    }

    void addNode(int id){
        this.nodes.add(new Node(id));
    }

    void connect(int x, int y){
        Node a = this.nodes.get(x);
        Node b = this.nodes.get(y);
        a.setNext(b);
        b.degree ++;
    }

    ArrayList<Integer> topoSort(){
        PriorityQueue<Node> queue_deg = new PriorityQueue<Node>(
            new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    return o1.degree - o2.degree;
                };
            }
        );
        PriorityQueue<Node> queue_id = new PriorityQueue<>(
            new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2){
                    return o1.id - o2.id;
                }
            }
        );
        
        for(int i = 1; i <= nodes.size() - 1; i++){
            queue_deg.add(nodes.get(i));
        }

        ArrayList<Integer> rst = new ArrayList<>();
        while(queue_deg.size() > 0 || queue_id.size() > 0){
            while(queue_deg.size() > 0 && queue_deg.peek().degree == 0){
                queue_id.add(queue_deg.poll());
            }

            Node node = queue_id.poll();
            node.delChildrenDegree();
            rst.add(node.id);
            while(queue_deg.size() > 0 && queue_deg.peek().degree == 0){
                queue_id.add(queue_deg.poll());
            }
        }
        
        return rst;
    }
}