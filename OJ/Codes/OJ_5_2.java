import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OJ_5_2 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));
        tk.nextToken();
        int T = (int) tk.nval;
        long total_cost;

        for(int i = 1; i <= T; i++){
            total_cost = 0;
            tk.nextToken();
            int N = (int) tk.nval;

            PriorityQueue<Student> students = new PriorityQueue<>(new Comparator<Student>() {

                @Override
                public int compare(Student o1, Student o2) {
                    return o2.m - o1.m;
                }
                
            });
            for(int j = 0; j < N; j++){
                tk.nextToken();
                int mi = (int) tk.nval;
                tk.nextToken();
                int ci = (int) tk.nval;
                
                students.add(new Student(mi, ci));
            }
            

            PriorityQueue<Student> S = new PriorityQueue<>(new Comparator<Student>() {
                @Override
                public int compare(Student o1, Student o2) {
                    return o1.c - o2.c;
                }
            });

            int layer = students.peek().m;
            int bought = 0;
            while(!students.isEmpty()){
                while(!students.isEmpty() && students.peek().m == layer){
                    S.add(students.poll());
                }

                if(students.size() + bought < layer){
                    int toBuy = layer - students.size() - bought;
                    for(int cnt = 0; cnt < toBuy; cnt++){
                        total_cost += (long)S.poll().c;
                        bought ++;
                    }
                }
                if(!students.isEmpty()) layer = students.peek().m;
            }

            System.out.println(total_cost);
        }



    }
}

class Student{
    public int m;
    public int c;

    Student(int mi, int ci){
        this.m = mi;
        this.c = ci;
    }

}