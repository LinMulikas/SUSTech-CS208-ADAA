import java.util.ArrayList;

public class Graph{
    int[][] ad;
    int[] depth;


    Graph(int size){
        this.ad = new int[size + 1][size + 1];
        this.depth = new int[size + 1];


        for(int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                this.ad[i][j] = -1;
                this.ad[i][j] = -1;
            }
        }
    }

    void connectEdge(int[][] edges){
        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];

            this.ad[a][b] = 1;
            this.ad[b][a] = 1;
        }
    }

    void addDiagEdge(int[][] edges){
        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];

            this.ad[a][b] = 1;
        }
    }

    void BFS(int id){
        ArrayList<Integer> children = new ArrayList<>();
    }
}
