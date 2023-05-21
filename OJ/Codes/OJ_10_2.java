import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class OJ_10_2 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));
        tk.nextToken();
        int n = (int) tk.nval;
        for(int i = 0; i < n; i++){
            tk.nextToken();
            String str = tk.sval;
            char[] chars = str.toCharArray();
            System.out.println(contest(chars, 0, chars.length - 1));
        }
    }

    

    static String contest(char[] chars, int lhs, int rhs){
        if(rhs - lhs == 1){
            if(chars[lhs] != chars[rhs]) return "Alice";
            else return "Draw";
        }
        if(chars[lhs] == chars[rhs]){
            char side = chars[lhs];
            if(side > chars[lhs + 1] && side > chars[rhs + 1]){
                return "Bob";
            }else{
                return contest(chars, lhs + 1, rhs - 1);
            }
        }else{
            if(chars[lhs] < chars[rhs]){
                if(chars[lhs + 1] < chars[lhs]){
                    return "Bob";
                }else if(chars[lhs + 1] > chars[lhs]){
                    return "Alice";
                }else{
                    return contest(chars, lhs + 2, rhs);
                }
            }else{
                if(chars[rhs - 1] < chars[rhs]){
                    return "Bob";
                }else if(chars[rhs - 1] > chars[rhs]){
                    return "Alice";
                }else{
                    return contest(chars, lhs, rhs - 2);
                }
            }
        }
    }
}
