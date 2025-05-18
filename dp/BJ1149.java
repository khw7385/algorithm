package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1149 {
    private static int N;
    private static int[][] costOfHouse;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costOfHouse = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                costOfHouse[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][3];
    }

    private static void solve(){
        // 초기화
        dp[1][0] = costOfHouse[1][0];
        dp[1][1] = costOfHouse[1][1];
        dp[1][2] = costOfHouse[1][2];

        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 3; j++){
                if(j == 0) dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costOfHouse[i][0];
                else if(j == 1) dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costOfHouse[i][1];
                else if(j == 2) dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costOfHouse[i][2];
            }
        }

        System.out.println(Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]));
    }
}
