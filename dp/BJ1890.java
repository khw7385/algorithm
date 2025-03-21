package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1890 {
    private static int[][] matrix;
    private static int[] dx = {1, 0};
    private static int[] dy = {0, 1};
    private static int[][] dp;

    // 40,000B 
    // 1MB = 1000KB = 1,000,000B

    public static void main(String[] args) throws IOException{
        readInput();
        solveUsingBottomUp();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        matrix = new int[n][n];
        dp = new int[n][n];

        for(int i = 0; i < n; i++){
            String[] numString = br.readLine().split(" ");
            
            for(int j = 0; j < n; j++){
                matrix[i][j] = Integer.parseInt(numString[j]);
            }        
        }
    }

    // 메모리 초과 발생
    private static void solveUsingTopDown(){
        System.out.println(dfs(0, 0));
    }

    private static void solveUsingBottomUp(){
        int size = matrix.length - 1;
        
        // dp 배열 초기화(dp[0][0]은 목적지이므로 1로 초기화)
        dp[0][0] = 1;

        for(int i = size; i >= 0; i--){
            for(int j = size; j >= 0; j--){
                if(i == size && j == size) continue;

                if(size - i - matrix[i][j] >= 0){
                    dp[size - i][size - j] += dp[size - i - matrix[i][j]][size - j];
                }

                if(size - j - matrix[i][j] >= 0){
                    dp[size - i][size - j] += dp[size - i][size - j - matrix[i][j]];
                }
            }
        }

        System.out.println(dp[size][size]);
    }

    private static int dfs(int x, int y){
        // 종료조건
        // 1. 마지막에 도달했다면
        if(x == matrix.length - 1 && y == matrix.length - 1){
            return 1; 
        }

        // 2. dp 배열에 값이 존재한다면(중복 제거)
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        int count = 0;

        for(int i = 0; i < 2; i++){
            int nextX = x + dx[i] * matrix[x][y];
            int nextY = y + dy[i] * matrix[x][y];

            if(nextX >= matrix.length || nextY >= matrix.length){
                continue;
            }
            
            count += dfs(nextX, nextY);
        }

        return dp[x][y] = count;
    }
}
