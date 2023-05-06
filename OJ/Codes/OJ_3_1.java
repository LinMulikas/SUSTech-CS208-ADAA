import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class OJ_3_1 {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tk = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        tk.nextToken();
        int n = (int)tk.nval;
        tk.nextToken();
        int m = (int)tk.nval;

        long MAX_Capablity = 0;
        int[] volume = new int[m + 1];
        int[] location = new int[n + 1];

        for(int i = 1; i <= m; i++){
            tk.nextToken();
            volume[i] = (int)tk.nval;
            MAX_Capablity += volume[i];
        }

        for(int i = 1; i <= n; i++){
            tk.nextToken();
            location[i] = (int)tk.nval;
        }

        Arrays.sort(volume);
        long result = 0;
        
        if(MAX_Capablity <= n){
            int capability = 0;
            for(int i = 1; i <= m; i++){
                capability += volume[i];
                result += 2*(long)location[capability];
            }
        }else{
            int id_backet = m;
            int leftApples = n;

            while(leftApples >= 0){
                result += 2*(long)location[leftApples];
                if(volume[id_backet] > leftApples){
                    break;
                }else{
                    leftApples -= volume[id_backet];
                    id_backet --;
                }
            }
        }

        System.out.println(result);
        in.close();
    }
}