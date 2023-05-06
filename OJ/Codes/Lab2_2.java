// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.PriorityQueue;

// public class Lab2_2 {
//     public static void main(String[] args) throws Exception{
//         BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//         String[] line = in.readLine().split(" ");
//         int T = Integer.parseInt(line[0]);
//         ArrayList<Integer> result = new ArrayList<>();

//         for(int i = 1; i <= T; i++){
//             line = in.readLine().split(" ");
//             int n = Integer.parseInt(line[0]);
//             int m = Integer.parseInt(line[1]);
//             DualGraph graph = new DualGraph(n);

//             for(int j = 1; j <= m; j++){
//                 line = in.readLine().split(" ");
//                 int a = Integer.parseInt(line[0]);
//                 int b = Integer.parseInt(line[1]);
//                 graph.connect(a, b);
//             }

//             result.add(graph.searchMiniCircle());
//         }

//         for(int res : result){
//             System.out.println(res);
//         }
//     }
// }

// class DualGraph{
//     int size;
//     Node[] nodesOut;
//     Node[] nodesIn;

//     DualGraph(int size){
//         this.size = size;
//         this.nodesOut = new Node[size + 1];
//         this.nodesIn = new Node[size + 1];

//         for(int i = 1; i <= size; i ++){
//             this.nodesIn[i] = new Node(i);
//             this.nodesOut[i] = new Node(i);
//         }
//     }



//     public int searchMiniCircle(){
//         int result = -1;

//         PriorityQueue<Node> queue = new PriorityQueue<>();
        
//         for(int i = 1; i <= this.size; i++){
//             queue.add(this.nodesIn[i]);
//             queue.add(this.nodesOut[i]);
//         }
        
//         while(!queue.isEmpty()){
//             Node tmp = queue.poll();
//             if(tmp.degree <= 1){
//                 this.fakeDelNode(tmp);
//                 continue;
//             }else{
//                 int tryAnswer = this.BFSMiniCircle(tmp);
//                 if(tryAnswer != -1){
//                     if(result == -1){
//                         result = tryAnswer;
//                     }else{
//                         result = Math.min(result, tryAnswer);
//                     }
//                 }
//                 this.fakeDelNode(tmp);
//             }
//         }

//         return result;
//     }

//     public void connect(int out_id, int in_id){
//         Node out = nodesOut[out_id];
//         Node in = nodesIn[in_id];

//         out.children.add(in);
//         out.degree ++;
//         in.children.add(out);
//         in.degree ++;
//     }

//     private void fakeDelNode(Node toDel){
//         toDel.searched = true;
//         for(Node node : toDel.children){
//             node.degree --;
//         }
//     }


//     private int BFSMiniCircle(Node s){
//         int[] depth = new int[this.size + 1];
//         HashMap<Node, Integer> getDepth = new HashMap<>();
//         for(int i = 1; i <= this.size; i++){
//             depth[i] = -1;
//         }

//         ArrayList<Node> toSearch = new ArrayList<>();
//         ArrayList<Node> next = new ArrayList<>();

//         toSearch.add(s);
//         depth[s.id] = 0;
//         getDepth.put(s, 0);

//         while(!toSearch.isEmpty()){
//             while(!toSearch.isEmpty()){
//                 Node node = toSearch.remove(0);
//                 for(Node child : node.children){
//                     if(child.searched) continue;
//                     if(depth[child.id] == -1){
//                         depth[child.id] = depth[node.id] + 1;
//                         next.add(child);
//                     }else{
//                         if(depth[child.id] < depth[node.id]) continue;
//                         if(depth[child.id] == depth[node.id]){
//                             return 2*depth[child.id] + 1;
//                         }else{
//                             return 2*depth[child.id];
//                         }
//                     }
//                 }
//             }
//             toSearch.addAll(next);
//             next.clear();
//         }

//         return -1;
//     }


//     private class Node implements Comparable<Node>{
//         int id;
//         int degree = 0;
//         boolean searched = false;
//         ArrayList<Node> children = new ArrayList<>();

//         Node(int x){
//             this.id = x;
//         }

//         public int compareTo(Node o) {
//             return this.degree - o.degree;
//         }

//     }
// }