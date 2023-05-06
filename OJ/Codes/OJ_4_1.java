import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class OJ_4_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String line = in.readLine();
        char[] S = line.toCharArray();

        int N = Integer.parseInt(in.readLine());
        
        int cnt = 0;

        for(int i = 0; i < N; i++){
            String W = in.readLine();
            ArrayDeque<Integer> list_lhs = KMPMatchAll(String.valueOf(S), W, KMPTable(W));
            
            ArrayDeque<Interval> queue = new ArrayDeque<>();
            ArrayDeque<Integer> splitPoints = new ArrayDeque<>();

            for(int lhs : list_lhs){
                queue.addLast(new Interval(lhs, lhs + W.length() - 1));
            }

            while(!queue.isEmpty()){
                while(!queue.isEmpty()){
                    Interval tmp = queue.poll();
                    while(!queue.isEmpty() && queue.peek().lhs <= tmp.rhs){
                        queue.poll();
                    }
                    splitPoints.addLast(tmp.rhs);
                }
            }

            //TODO: Split
            for(int index : splitPoints){
                S[index] = ' ';
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    static ArrayDeque<Integer> KMPMatchAll(String S, String W, int[] T){
        // S.length > W.length
        ArrayDeque<Integer> result = new ArrayDeque<>();
        
        int i = 0;
        int j = 0;
        while(i < S.length()){
            if(j == W.length()){
                j = T[j];
            }
            if(S.length() - i < W.length() - j){
                break;
            }
            if(S.charAt(i) == W.charAt(j)){
                i ++;
                j ++;
                if(j == W.length()){
                    result.addLast(i - j);
                }
            }else{
                if(T[j] == -1){
                    i ++;
                    j = 0;
                }else{
                    j = T[j];
                }
            }
        }
        
        return result;
    }

    static int[] KMPTable(String W){
        int n = W.length();
        int[] T = new int[n + 1];
        T[0] = -1;
        if(n == 1) return T;

        T[1] = 0;
        int pos = 2;

        while(pos <= n){
            T[pos] = W.charAt(T[pos - 1]) == W.charAt(pos - 1) ? T[pos - 1] + 1 : 0;
            pos ++;
        }

        return T;
    }
}

class Interval implements Comparable<Interval>{
    int lhs;
    int rhs;

    Interval(int x, int y){
        this.lhs = x;
        this.rhs = y;
    }

    @Override
    public int compareTo(Interval o) {
        return this.rhs - o.rhs;
    }

    static int breakPoints(PriorityQueue<Interval> queue){
        ArrayList<Integer> result = new ArrayList<>();
        int cnt = 0;

        while(true){
            if(!queue.isEmpty()){
                while(!queue.isEmpty()){
                    Interval tmp = queue.poll();
                    int point = tmp.rhs;
                    while(!queue.isEmpty() && point >= queue.peek().lhs){
                        queue.poll();
                    }
                    result.add(point);
                    cnt ++;
                }
            }else{
                break;
            }
        } 
        return cnt;
    }
}