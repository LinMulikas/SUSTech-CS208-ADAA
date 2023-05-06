import java.util.ArrayList;

public class Lab_01_ADAA{
    public static void main(String[] args) {
        System.out.println("Practice 1");
        Hanoi(3, 'A', 'C', 'B');

        System.out.println("Practice 2");
        System.out.println(prac2(1));
        System.out.println(prac2(2));
        System.out.println(prac2(3));
        System.out.println(prac2(4));
        System.out.println(prac2(5));

        System.out.println("Practice 3");
        prac3("abc");

        System.out.println("Practice 4");
        prac4("abc");
    }

    static void Hanoi(int n, char start, char end, char aux){
        if(n == 1){
            System.out.println(
                "Move disk 1 from " + start + " to " + end 
            );
            return;
        }
        Hanoi(n - 1, start, aux, end);
        System.out.println(
            "Move disk " + n + " from " + start + " to " + end
        );
        Hanoi(n - 1, aux, end, start);
    }

    static long prac2(int n){
        long res = 0;

        if(n == 0) return 0;
        if(n == 1) return 1;
        if(n == 2) return 2;

        long[] an = new long[n + 1];
        an[1] = 1;
        an[2] = 2;

        for(int i = 3; i <= n; i++){
            an[i] = an[i - 1] + an[i - 2];
        }

        res = an[n];
        return res;
    };

    static void prac3(String str){
        ArrayList<String> strs = new ArrayList<>();
        perm(strs, str);
        
        for(String i : strs){
            System.out.println(i);
        }
    }

    static void perm(ArrayList<String> answer, String rhs){
        if(rhs.length() > 0){
            String head = rhs.substring(0, 1);
            String other = rhs.substring(1, rhs.length());

            insertAllCases(answer, head);
            if(other.length() > 0) perm(answer, other);
        }
    }

    static void insertAllCases(
        ArrayList<String> answer, String c){
            if(answer.isEmpty()){
                answer.add(c);
                return;
            }
            ArrayList<String> _answer = new ArrayList<>();

            for(int i = 0; i < answer.size(); i++){
                String str = answer.get(i);
                for(int index = 0; index <= str.length(); index ++){
                    String lhs = str.substring(0, index);
                    if(index >= str.length()){
                        _answer.add(new String(lhs + c));
                    }else{
                        _answer.add(new String(
                            lhs + c + str.substring(index, str.length())));
                    }
                    
                }
            }
            answer.clear();
            answer.addAll(_answer);
    }

    

    static void prac4(String str){
        ArrayList<String> ans = combString(str);
        for(String i : ans){
            System.out.println(i);
        }
    }
    
    static ArrayList<String> combString(String str){
        ArrayList<String> ans = new ArrayList<>();

        StringBuilder strBuilder = new StringBuilder();
        int n = str.length();
        // Add all cases.
        for(int i = 1; i <= Math.pow(2, n); i++){
            String charFunction = toBinary(i, n);

            for(int j = 0; j < charFunction.length(); j++){
                if(charFunction.charAt(j) == '0'){
                    strBuilder.append("");
                }else{
                    strBuilder.append(str.charAt(j));
                }
            }

            if(strBuilder.length() == 0) continue;

            ans.add(strBuilder.toString());
            strBuilder.delete(0, strBuilder.length());
        }

        return ans;
    }

    public static String toBinary(int num, int digits) {
        int value = 1 << digits | num;
        String bs = Integer.toBinaryString(value); //0x20 | 这个是为了保证这个string长度是6位数
        return  bs.substring(1);
    }
}

