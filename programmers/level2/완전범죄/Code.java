package programmers.level2.완전범죄;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] info = {
            { 3, 3 },
            { 3, 3 }, 
        };

        int n = 7;
        int m = 1;

        int answer = solution.solution(info, n, m);
        System.out.println(answer);
    }
    
    private static class Solution{
        private static final int INF = 200;

        public int solution(int[][] info, int n, int m) {
            int[][] dp = new int[info.length + 1][m];

            // 초기화
            for(int i = 1; i < m; i++){
                dp[0][i] = INF;
            }

            for(int i = 0 ; i < info.length; i++){
                for(int j = 0; j < m; j++){
                    if(dp[i][j] + info[i][0] >= n) dp[i + 1][j] = INF;
                    else  dp[i + 1][j] = dp[i][j] + info[i][0];
                    
                    if(j >= info[i][1]) dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j - info[i][1]]);
                }
            }

            int result = Integer.MAX_VALUE;
            for(int i = 0; i < m; i++){
                result = Math.min(dp[info.length - 1][i], result); 
            }

            if (result == INF) return -1;

            return result;
        }
    
    }
}
