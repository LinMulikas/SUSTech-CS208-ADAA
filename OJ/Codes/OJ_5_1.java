import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class OJ_5_1 {
    public static void main(String[] args) throws IOException {
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));

        tk.nextToken();
        int N = (int) tk.nval;
        
        tk.nextToken();
        int coins = (int) tk.nval;

        int[] P = new int[N + 1];
        int[] W = new int[N + 1];

        for(int i = 1; i <= N; i++){
            tk.nextToken();
            P[i] = (int) tk.nval;
        }

        for(int i = 1; i <= N; i++){
            tk.nextToken();
            W[i] = (int) tk.nval;
        }

        long cost = 0;

        PriorityQueue<Integer> history = new PriorityQueue<>();

        for(int i = 1; i <= N; i++){
            if(P[i] % 100 == 0) continue;
            history.add(W[i] * (100 - mod(P[i])));
            coins -= mod(P[i]);
            if(coins < 0){
                coins += 100;
                cost += history.poll();
            }
        }

        System.out.println(cost);
    }

    static int mod(int x){
        if(x >= 0 && x <= 100) return x;
        while(x < 100){
            x += 100;
        }
        return x - (x/100)*100;
    }
}