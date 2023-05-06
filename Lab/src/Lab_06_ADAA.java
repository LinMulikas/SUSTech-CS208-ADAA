import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Lab_06_ADAA {
    public static void main(String[] args) {
        PriorityQueue<Interval> queue = new PriorityQueue<>();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        for(int i = 0; i < N; i++){
            int a = in.nextInt();
            int b = in.nextInt();
            queue.add(new Interval(a, b));
        }

        ArrayList<Integer> result = new ArrayList<>();

        while(true){
            if(!queue.isEmpty()){
                while(!queue.isEmpty()){
                    Interval tmp = queue.poll();
                    int point = tmp.rhs;
                    while(!queue.isEmpty() && point >= queue.peek().lhs){
                        queue.poll();
                    }
                    result.add(point);
                }
            }else{
                break;
            }
        } 
        System.out.println(result.size());
        in.close();
    }



}

class Interval implements Comparable<Interval>{
    int lhs;
    int rhs;

    Interval(int l, int r){
        this.lhs = l;
        this.rhs = r;
    }

    
    @Override
    public int compareTo(Interval o) {
        return this.rhs - o.rhs;
    }
}