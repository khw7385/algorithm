package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ2758 {
    private static long[][] dpForTopDown;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int m = Integer.parseInt(line[1]);
            dpForTopDown = new long[n + 1][m + 1];
            Arrays.fill(dpForTopDown, -1);
            solveUsingTopDown(n, m);
            dpForTopDown = null;
        }    
    }

    private static void solveUsingBottomUp(int pickNum, int maxNum){
        long[][] dp = new long[pickNum + 1][maxNum + 1];
        
        for(int i = 1 ; i <= maxNum; i++){
            dp[1][i] = i;
        }

        for(int i = 2; i <= pickNum; i++){
            for(int j = 1; j <= maxNum; j++){
                dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
            }
        }
        System.out.println(dp[pickNum][maxNum]);
    }

    private static void solveUsingTopDown(int pickNum, int maxNum){
        for(int i = 1 ; i <= maxNum; i++){
            dpForTopDown[1][i] = i;
        }
        System.out.println(count(pickNum, maxNum));
    }
    
    private static long count(int pickNum, int maxNum){
        if(dpForTopDown[pickNum][maxNum] != -1){
            return dpForTopDown[pickNum][maxNum];
        }

        if(maxNum == 1){
            return 0;
        }

        return dpForTopDown[pickNum][maxNum] = count(pickNum - 1, maxNum / 2) + count(pickNum, maxNum - 1);
    }
}
