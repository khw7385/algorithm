package programmers.level4.경사로의개수;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid = {
            { 3, 4, 6, 5, 3 }, 
            { 3, 5, 5, 3, 6 }, 
            { 5, 6, 4, 3, 6 }, 
            { 7, 4, 3, 5, 0 }
        };
        int[] d = { 1, -2, -1, 0, 2 };
        int k = 2;

        int answer = solution.solution(grid, d, k);
        System.out.println(answer);
    }
    

    public static class Solution{
        private static final Integer MOD = 1000000007;
        private int[][] grid;
        private int n, m;
        private int[] d;

        private int[] dx = { 1, 0, -1, 0 };
        private int[] dy = { 0, 1, 0, -1 };

        public int solution(int[][] grid, int[] d, int k){
            init(grid, d);
            int[][] oneTimeResult = calculateOneTimeSlope();

            int[][] result = power(k, oneTimeResult);

            int answer = 0;
            for(int i = 0; i < n * m; i++){
                for(int j = 0; j < n * m; j++){
                    answer += result[i][j];
                }
            }
            return answer;
        }

        private void init(int[][] grid, int[] d){
            this.n = grid.length;
            this.m = grid[0].length;

            this.grid = new int[grid.length][grid[0].length];

            for(int i = 0; i < grid.length; i++){
                this.grid[i] = grid[i].clone();
            }

            this.d = d.clone();
        }

        private int[][] calculateOneTimeSlope(){
            int size = n * m;

            int[][] result = new int[size][size];

            for(int start = 0; start < size; start++){

                int[][] dp = new int[d.length + 1][size];
                
                dp[0][start] = 1;
            
                for(int step = 0 ; step < d.length; step++){
                    for(int end = 0; end < size; end++){
                        if(dp[step][end] == 0) continue;

                        int row = end / m;
                        int col = end % m;

                        for(int i = 0 ; i < 4; i++){
                            int nextRow = row + dx[i];
                            int nextCol = col + dy[i];

                            if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) continue;
                            if(grid[nextRow][nextCol] - grid[row][col] != d[step]) continue;

                            dp[step + 1][nextRow * m + nextCol] = (dp[step + 1][nextRow * m + nextCol] + dp[step][end]) % MOD;
                        }
                    }
                }

                for(int end = 0; end < size; end++){
                    result[start][end] = dp[d.length][end];
                }
            }

            return result;
        }

        private int[][] combine(int[][] frontMatrix, int[][] backMatrix){
            int size = n * m;

            int[][] result = new int[size][size];

            for(int start = 0 ; start < size; start++){
                for(int mid = 0; mid < size; mid++){
                    if(frontMatrix[start][mid] == 0) continue;
                    
                    for(int end = 0; end < size; end++){
                        if(backMatrix[mid][end] == 0) continue;

                        int frontValue = frontMatrix[start][mid];
                        int backValue = backMatrix[mid][end];

                        long multValue = ((long)frontValue * (long)backValue) % MOD;
                        
                        result[start][end] = (result[start][end] + (int)multValue) % MOD;
                    }
                }
            }
            return result;
        }

        private int[][] power(int k, int[][] base){
            int size = n * m;
            int[][] result = new int[size][size];
            
            // 이 초기화가 잘못됨!!
            for(int i = 0 ; i < size; i++){
                result[i][i] = 1;
            }

            while(k > 0){
                if(k % 2 == 1) result = combine(result, base);
                base = combine(base, base);
                k = k >> 1;
            }

            return result;
        }
    }
}
