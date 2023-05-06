import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


class Lab_05_ADAA_2{
    public static void main(String[] args) {
        PriorityQueue<Time> queue = new PriorityQueue<>();
        Scanner in = new Scanner(System.in);
        
        for(int i = 1; i <= 8; i++){
            Time time = new Time(in.next(), in.nextInt(), in.nextInt());
            queue.add(time);
        }
        int last = 0;
        ArrayList<Time> rst = new ArrayList<>();
        for(Time time : queue){
            if(time.a >= last){
                rst.add(time);
                last = time.b;
            }
        }

        System.out.println();
        for(Time time : rst){
            System.out.printf("%s %d %d\n", time.name, time.a, time.b);
        }
        in.close();
    }
}

class Time implements Comparable<Time>{
    String name;
    int a;
    int b;

    Time(String name, int a, int b){
        this.name = name;
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Time o) {
        return this.b - o.b;
    }
}