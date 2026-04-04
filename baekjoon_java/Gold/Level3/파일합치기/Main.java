package baekjoon_java.Gold.Level3.파일합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] files;
    private static int[] sum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        
        for(int i = 0; i < t; i++){
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            files = new int[n + 1];
            for(int j = 1; j <= n; j++){
                files[j] = Integer.parseInt(st.nextToken());
            }

            sum = new int[n + 1];
            for(int j = 1; j <= n; j++){
                sum[j] = sum[j - 1] + files[j];
            }

            solve();
        }
    }

    private static void solve(){
        int[][] dp = new int[n + 1][n + 1];
        
        for(int len = 1; len < n; len++){ // 구간 길이
            for(int i = 1; i + len <= n; i++){ // 시작점
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++){  // i 와 j 사이를 자르는 지점 k를 옮겨가면 최솟값 찾기
                    int cost = dp[i][k] + dp[k + 1][j] + sum[j] - sum[i - 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        System.out.println(dp[1][n]);
    }
}
