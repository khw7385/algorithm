package baekjoon_java.Gold.Level4.타일채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        if(n % 2 == 1){
            System.out.println(0);
            return;
        }
        solve();
    }

    private static void solve(){
        int[] dp = new int[n + 1];
        int[] sum = new int[n + 1];

        dp[0] = 1;
        for(int i = 2; i <= n; i += 2){
            if(i >= 4){
                dp[i] = dp[i - 2] * 3 + sum[i - 4] * 2;
            }else{
                dp[i] = dp[i - 2] * 3;
            }

            sum[i] = dp[i] + sum[i - 2];
        }

        System.out.println(dp[n]);
    }
}
