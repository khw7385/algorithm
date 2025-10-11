package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1915 {
    private static int n, m;
    private static int[][] arr;
    public static void main(String[] args) {
        initInput();
        int answer = solve();
        System.out.println(answer);
    }

    private static void initInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n][m];

            for(int i = 0; i < n; i++){
                String line = br.readLine();

                for(int j = 0; j < line.length(); j++){
                    arr[i][j] = line.charAt(j) - '0';
                }
            }
        }catch(IOException e){

        }
    }

    private static int solve(){
        int[][] dp = initDp();

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(arr[i][j] == 0) dp[i][j] = 0;
                else{
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        int result = 0;
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){
                result = Math.max(result, dp[i][j]);
            }
        }

        return result * result;
    }

    private static int[][] initDp(){
        int[][] dp = new int[n][m];

        for(int i = 0; i < m; i++){
            dp[0][i] = arr[0][i] == 1 ? 1 : 0;
        }

        for(int i = 0 ; i < n ;i++){
            dp[i][0] = arr[i][0] == 1 ? 1 : 0;
        }

        return dp;
    }
}
