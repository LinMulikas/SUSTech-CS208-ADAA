import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Lab_08_ADAA {
    public static void main(String[] args) throws IOException {
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));
        tk.nextToken();
        int T = (int) tk.nval;

        for(int t = 0; t < T; t++){
            tk.nextToken();
            int n = (int) tk.nval;
            tk.nextToken();

            int k = (int) tk.nval;
            
            PriorityQueue<Integer> queue1 = new PriorityQueue<>(Comparator.comparingInt(a -> -a));
            PriorityQueue<Integer> queue2 = new PriorityQueue<>(Comparator.comparingInt(a -> -a));

            for(int i = 0; i < n; i++){
                tk.nextToken();
                queue1.add((int) tk.nval);
                queue2.add((int) tk.nval);
            }

            System.out.printf("%d %d\n", maxMerge(queue1, k), minMerge(queue2, k));
        }
    }

    static int minMerge(PriorityQueue<Integer> queue, int k){
        int ans = 0;
        if(queue.isEmpty()) return 0;
        if(queue.size() <= k){
            while(!queue.isEmpty()){
                ans += queue.poll();
            }
            return ans;
        }else{
            int cnt = 0;
            while(cnt < k - 1){
                ans += queue.poll();
                cnt ++;
            }
            int sum = queue.stream().mapToInt(i -> (int) i).sum();

            return ans + sum + minMerge(queue, k);
        }
    }

    static int maxMerge(PriorityQueue<Integer> queue, int k){
        int ans = 0;
        while(queue.size() > 1){
            int sum = queue.poll() + queue.poll();
            ans += sum;
            queue.add(sum);
        }
        return ans;
    }
}
