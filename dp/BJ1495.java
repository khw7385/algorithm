package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1495 {
    private static int N, S, M;
    private static int[] songs;
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer line1 = new StringTokenizer(br.readLine());

        N = Integer.parseInt(line1.nextToken());
        S = Integer.parseInt(line1.nextToken());
        M = Integer.parseInt(line1.nextToken());

        songs = new int[N + 1];
        StringTokenizer line2 = new StringTokenizer(br.readLine());
        
        int line2TokenNum= line2.countTokens();
        for(int i = 1; i <= line2TokenNum; i++){
            songs[i] = Integer.parseInt(line2.nextToken());
        }
    }

    private static void solve(){
        // dp 초기화
        dp = new int[N + 1][M + 1];
        if(S + songs[1] <= M) dp[1][S + songs[1]] = 1;
        if(S - songs[1] >= 0) dp[1][S - songs[1]] = 1;

        for(int i = 2; i <= N; i++){
            for(int j = 0; j <= M; j++){
                if(dp[i - 1][j] == 1){
                    if(j + songs[i] <= M) dp[i][j + songs[i]] = 1;
                    if(j - songs[i] >= 0) dp[i][j - songs[i]] = 1;
                }
            }
        }

        int result = - 1;

        for(int i = 0; i <= M; i++){
            if(dp[N][i] == 1){
                result = i;
            }
        }
        System.out.println(result);;
    }
}
