package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ5557{
    private static int n;
    private static int[] operands;
    private static long[][] dp;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        operands = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    private static void solve(){
        dp = new long[n][21];
        
        dp[0][operands[0]] = 1;

        for(int i = 1; i <= n - 2; i++){
            for(int j = 0; j <= 20; j++){
                if(dp[i - 1][j] != 0){
                    if(j + operands[i] <= 20){
                        dp[i][j + operands[i]] += dp[i - 1][j];
                    }

                    if(j - operands[i] >= 0){
                        dp[i][j - operands[i]] += dp[i - 1][j];
                    }
                }
            }
        }

        System.out.println(dp[n - 2][operands[n - 1]]);
    }
}