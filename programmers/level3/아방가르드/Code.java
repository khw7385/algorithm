package programmers.level3.아방가르드;

public class Code {
    public static void main(String[] args) {
        
    }

    class Solution {
        private static final int MOD_NUM = 1000000007;
        
        public int solution(int n) {    
            int[] dp = new int[Math.max(7, n + 1)];
            dp[0] = 1; dp[1] = 1; dp[2] = 3; dp[3] = 10; dp[4] = 23; dp[5] = 62; dp[6] = 170;
            
            if (n <= 6) return dp[n];
            
            for (int i = 7; i <= n; i++) {
                long val = (long) dp[i - 1] 
                         + (long) dp[i - 2] * 2 
                         + (long) dp[i - 3] * 6 
                         + (long) dp[i - 4] 
                         - (long) dp[i - 6];
                
                val = (val % MOD_NUM + MOD_NUM) % MOD_NUM;
                dp[i] = (int) val;
            }        
            
            return dp[n];
        }
    }
}
