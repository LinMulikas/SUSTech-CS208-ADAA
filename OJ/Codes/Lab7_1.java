import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Lab7_1{
    public static void main(String[] args) throws Exception{
        StreamTokenizer tk = new StreamTokenizer(new InputStreamReader(System.in));

        tk.nextToken();
        long n = (long) tk.nval;

        tk.nextToken();
        long lhs = (long) tk.nval;

        tk.nextToken();
        long rhs = (long) tk.nval;
        
        System.out.println(Long.MAX_VALUE);
        System.out.println(getSum(lhs, rhs, n));
    }

    public static long getOrder(long x){
        return (long)(Math.log(x)/Math.log(2));
    }

    public static long order2Length(long ord){
        return (long)Math.pow(2, ord + 1) - 1;
    }
    
    public static long getLen(long x){
        return (long) order2Length(getOrder(x));
    }

    public static long getSum(long lhs, long rhs, long n){
        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }
        long sum = 0;
        long len = getLen(n);
        long mid = (len + 1)/2;

        sum += n % 2;

        if(lhs < mid){
            long _lhs = lhs;
            long _rhs = Math.min(rhs, mid - 1);
            sum += getSum(_lhs, _rhs, n/2);
        }

        if(rhs > mid){
            long _lhs = 1;
            long _rhs = Math.max(lhs, 1);
            sum += getSum(_lhs, _rhs, n/2);
        }

        return sum;
    }
}