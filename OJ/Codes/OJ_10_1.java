import java.util.Scanner;
class Main{
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();

        int[] Value = new int[n + 1];
        int[] Cost = new int[n + 1];
        
        for(int i = 1; i <= n; i++){
            Value[i] = in.nextInt();
        }

        for(int i = 1; i <= n; i++){
            Cost[i] = in.nextInt();
        }
        
        int[][] ans = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++){
            for(int money = 0; money <= m; money++){
                if(money < Cost[i]){
                    ans[i][money] = ans[i - 1][money];
                }else{
                    ans[i][money] = Math.max(
                        ans[i - 1][money], 
                        ans[i - 1][money - Cost[i]] + Value[i]);
                }
            }
        }
        System.out.println(ans[n][m]);
    }
}