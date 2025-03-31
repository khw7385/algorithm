package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BJ15989 {
    private static int testCase;
    private static int[] inputs;
    private static int[][] dp;

    public static void main(String[] args) throws IOException{
        readInput();

        for(int i = 0; i < testCase; i++){
            dp = new int[inputs[i] + 1][4];
            solve(inputs[i]);
        }
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
        inputs = new int[testCase];

        for(int i = 0; i < testCase; i++){
            inputs[i] = Integer.parseInt(br.readLine());    
        }
    }

    private static void solve(int input){
        // 초기화
        if(input == 1){
            System.out.println(1);
            return;
        }

        if(input == 2){
            
        }

        for(int i = 1; i <= 3; i++){
            dp[i][i] = 1;
        }
        dp[2][1] = 1;


        for(int i = 3; i <= input; i++){
            for(int j = 1; j <= 3; j++){
                for(int k = j; k <= 3; k++){
                    dp[i][j] += dp[i - j][k];
                }
            }
        }

        long result = 0;
        for(int i = 1; i <= 3; i++){
            result += dp[input][i];
        }

        System.out.println(result);
    }
}
