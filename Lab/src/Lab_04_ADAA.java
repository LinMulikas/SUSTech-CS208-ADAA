import java.util.ArrayList;
import java.util.Scanner;

public class Lab_04_ADAA {
    public static void main(String[] args) {
            
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ids = new int[n + 1];

        for(int i = 1; i <= n; i++){
            ids[i] = in.nextInt();
        }

        int[] an = new int[n + 1];

        for(int i = 1; i <= n; i++){
            an[i] = in.nextInt();
        }

        Node[] nodes = A.createMap(ids, an);

        nodes[1].BFS();

        for(int i = 1; i <= n; i++){
            System.out.println(nodes[i].depth);
        }

        in.close();
    }


}

class A{

    


    public static Node[] createMap(int[] ids, int[] an){
        int n = ids.length - 1;

        Node[] nodes = new Node[n + 1];

        for(int i = 1; i <= n; i++){
            nodes[i] = new Node(i);
        }

        for(int i = 1; i < n; i++){
            nodes[i].children.add(nodes[i + 1]);
            if(an[i] > i){
                nodes[i].children.add(nodes[an[i]]);
            }
        }

        return nodes;
    }
}

class Node{
    int id = 0;
    int depth = 0;

    ArrayList<Node> children = new ArrayList<>();

    Node(int id){
        this.id = id;
        this.depth = this.id - 1;
    }

    void trySetDepth(int _depth){
        if(_depth <= this.depth){
            this.depth = _depth;
            this.BFS();
        }
    }

    void BFS(){
        for(Node node : this.children){
            node.trySetDepth(this.depth + 1);
        }
    }
}