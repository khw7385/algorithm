package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ12852 {
    private static int N;
    private static int[] dp;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }
    
    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];
    }

    private static void solve(){
        dp[1] = 0;

        for(int i = 2; i <= N; i++){
            dp[i] = dp[i - 1] + 1;
            if(i % 2 == 0) dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if(i % 3 == 0) dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }

        System.out.println(dp[N]);

        while(N != 1){
            System.out.print(N + " ");
            if(N % 3 == 0 && dp[N] - 1 == dp[N / 3]) N /= 3;
            else if(N % 2 == 0 && dp[N] - 1 == dp[N / 2]) N /= 2;
            else N -= 1;
        }
        System.out.print(1);
    }
}
