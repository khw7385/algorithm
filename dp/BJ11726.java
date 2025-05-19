package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ11726 {
    private static int N;
    private static int[] dp; 
    public static void main(String[] args) throws IOException {
        readInput();   
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
    }

    private static void solve(){
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2;i <= N; i++){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }

        System.out.println(dp[N]);
    }
}  
