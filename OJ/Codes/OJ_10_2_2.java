import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;

public class OJ_10_2_2 {
    public static void main(String[] args) throws Exception{
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));
        tk.nextToken();
        int n = (int) tk.nval;

        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < n; i++){
            tk.nextToken();
            char[] cs = tk.sval.toCharArray();
            ans.add(check(cs, 0, cs.length - 1));
        }
        for(String str : ans){
            System.out.println(str);
        }
    }

    static String check(char[] cs, int lhs, int rhs){
        if(rhs - lhs == 1){
            if(cs[lhs] == cs[rhs]) return "Draw";
        }else{
            if(cs[lhs] < cs[rhs]){
                if(cs[lhs + 1] < cs[lhs]) return "Bob";
                if(cs[lhs + 1] == cs[lhs]) return check(cs, lhs + 2, rhs);
                return "Alice";
            }else if(cs[rhs] < cs[lhs]){
                if(cs[rhs - 1] < cs[rhs]) return "Bob";
                if(cs[rhs - 1] == cs[rhs]) return check(cs, lhs, rhs - 2);
                return "Alice";
            }else{
                if(cs[lhs] < cs[lhs + 1] && cs[rhs] > cs[rhs - 1]) return "Alice";
    
                if(cs[lhs] > cs[lhs + 1] && cs[rhs] < cs[rhs - 1]) return "Alice";
    
                if(cs[lhs] < cs[lhs + 1] && cs[rhs] < cs[rhs - 1]){
                    if(cs[lhs + 1] == cs[rhs - 1]){
                        return check(cs, lhs + 1, rhs - 1);
                    }else{
                        return "Alice";
                    }
                }
    
                if(cs[lhs] > cs[lhs + 1] && cs[rhs] > cs[rhs - 1]){
                    return "Bob";
                }
    
                if(cs[lhs] == cs[lhs + 1] && cs[rhs] == cs[rhs - 1]) return check(cs, lhs + 1, rhs - 1);

                if(cs[lhs] == cs[lhs + 1]){
                    if(rhs - lhs == 3){
                        return "Alice";
                    }
                    if(cs[rhs - 1] > cs[rhs]) return check(cs, lhs + 1, rhs - 1);
                    if(cs[rhs - 1] < cs[rhs]) return check(cs, lhs + 2, rhs);
                    return check(cs, lhs + 1, rhs - 1);
                }
                if(cs[rhs] == cs[rhs - 2]){
                    if(rhs - lhs == 3){
                        return "Alice";
                    }
                    if(cs[lhs + 1] > cs[lhs]) return check(cs, lhs + 1, rhs - 1);
                    if(cs[lhs + 1] < cs[lhs]) return check(cs, lhs, rhs - 2);
                    return check(cs, lhs + 1, rhs - 1);
                }
            }
        }
        return check(cs, lhs + 1, rhs - 1);
    }
}
