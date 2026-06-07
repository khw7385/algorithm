package programmers.level3.보행자천국;
import java.util.*;

public class Code {
    public static void main(String[] args) {
        
    }    

    class Solution {
        int MOD = 20170805;
        int[] dx = { 1, 0 };
        int[] dy = { 0, 1 };
        
        int m, n;
        int[][] cityMap;
        int result = 0;
        int[][][] dp;
        
        public int solution(int m, int n, int[][] cityMap) {
            this.m = m; this.n = n; this.cityMap = cityMap;
        
            dp = new int[m][n][3];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }
            
            return dfs(0, 0, 2);
        }
        
        // dir -> 2: 정지, 0: 상하, 1: 좌우
        private int dfs(int curX, int curY, int dir){
            if(curX == m - 1 && curY == n - 1){
                return 1;
            }
            
            if(dp[curX][curY][dir] != -1) return dp[curX][curY][dir];
            
            dp[curX][curY][dir] = 0;
            for(int d = 0; d < 2; d++){
                int nextX = curX + dx[d];
                int nextY = curY + dy[d];
                
                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                if(cityMap[nextX][nextY] == 1) continue;
                if (cityMap[curX][curY] == 2) {
                    if (dir != d) continue;
                }
                dp[curX][curY][dir] = (dp[curX][curY][dir] + dfs(nextX, nextY, d)) % MOD;
            }
            
            return dp[curX][curY][dir];
        }
    }
}
