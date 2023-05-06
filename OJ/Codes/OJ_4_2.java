import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

class OJ_4_2{
    public static void main(String[] args) throws IOException {
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));
        tk.nextToken();
        
        int n = (int)tk.nval;

        ArrayList<Fruit> fruits = new ArrayList<>();

        for(int i = 0; i < n; i++){
            tk.nextToken();
            int lhs = (int)tk.nval;

            tk.nextToken();
            int rhs = (int)tk.nval;

            tk.nextToken();
            int value = (int)tk.nval;

            Fruit tmp = new Fruit(lhs, rhs, value);
            fruits.add(tmp);
        }

        Collections.sort(fruits, Comparator.comparingInt(f -> f.lhs));

        int[] TS = new int[n];
        int x = 0;
        
        for(int i = 0; i < n; i++){
            x = Math.max(x + 1, fruits.get(i).lhs);
            TS[i] = x;
            fruits.get(i).tid = i;
        }

        Collections.sort(fruits, Comparator.comparingInt(f -> -f.value));


        HashMap<Integer, Fruit> table = new HashMap<>();
        ArrayDeque<Fruit> S = new ArrayDeque<>();
        ArrayDeque<Fruit> queue = new ArrayDeque<>(fruits);

        while(!queue.isEmpty()){
            Fruit tmp = queue.poll();
            if(linearMatch(TS, table, tmp, tmp.lhs - 1)){
                S.addLast(tmp);
            }
        }
        
        long result = 0;
        for(Fruit f : S){
            result += (long)f.value;
        }
        System.out.println(result);
    }

    static boolean linearMatch(int[] TS, HashMap<Integer, Fruit> table, Fruit tmp, int tid){
        int location = TS[tid];
        if(location > tmp.rhs) return false;
        if(table.get(location) == null){
            table.put(location, tmp);
            return true;
        }
        Fruit that = table.get(location);
        if(tmp.rhs > that.rhs){
            return linearMatch(TS, table, tmp, tid + 1);
        }else{
            if(linearMatch(TS, table, that, tid + 1)){
                table.put(location, tmp);
                return true;
            }
        }
        return false;
    }
}

class Fruit{
    int lhs;
    int rhs;
    int value;
    int tid;

    Fruit(int x, int y, int value){
        this.lhs = x;
        this.rhs = y;
        this.value = value;
    }

}