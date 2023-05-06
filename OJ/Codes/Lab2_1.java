import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lab2_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] line = in.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int p = Integer.parseInt(line[2]);
        int q = Integer.parseInt(line[3]);
        ArrayList<Integer> checkPoints = new ArrayList<>();
        
        mGraph graph = new mGraph(n);
        for(int i = 1; i <= m; i++){
            line = in.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            
            graph.connect(a, b);
        }

        int max_check = 0;
        for(int i = 0; i < q; i++){
            int check_day = Integer.parseInt(in.readLine().split(" ")[0]);
            checkPoints.add(check_day);
            if(check_day >= max_check){
                max_check = check_day;
            }
        }

        int[] answer = graph.BFS(p, max_check);
        
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < q; i++){
            int index = checkPoints.get(i);
            if(index < n){
                str.append(answer[index]);
                str.append(" ");
            }else{
                str.append(answer[n - 1]);
                str.append(" ");
            }
        }
        
        System.out.println(str.toString());
    }
}

class mGraph{
    Node[] nodes;

    mGraph(int size){
        this.nodes = new Node[size + 1];
        for(int i = 0; i <= size; i++){
            this.nodes[i] = new Node(i);
        }
    }

    int[] BFS(int s, int q){
        int n = this.nodes.length - 1;
        int[] answer = new int[this.nodes.length - 1]; 

        ArrayList<Node> toSearch = new ArrayList<>();
        ArrayList<Node> next = new ArrayList<>();
        toSearch.add(nodes[s]);

        for(int i = 0; i < n; i++){
            if(toSearch.isEmpty()) break;
            while(!toSearch.isEmpty()){
                Node tmp = toSearch.remove(0);
                
                tmp.depth = i;
                answer[i] ++;

                for(Node node : tmp.children){
                    if(node.depth == -1){
                        next.add(node);
                    }
                }
            }
            toSearch.addAll(next);
            next.clear();
        }

        for(int i = 1; i < n; i++){
            answer[i] = answer[i] + answer[i - 1];
        }

        return answer;
    }


    void connect(int a, int b){
        Node A = nodes[a];
        Node B = nodes[b];

        A.children.add(B);
        B.children.add(A);
    }

    class Node {
        int depth = -1;
        int id;

        ArrayList<Node> children = new ArrayList<>();


        Node(int x){
            this.id = x;
        }
    }
}