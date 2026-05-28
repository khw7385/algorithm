package programmers.level3.보물찾기;

import java.util.function.Function;

public class Code {
    public static void main(String[] args) {
        
    }

    static class Solution {
       public int solution(int[] depth, int money, Function<Integer, Integer> excavate) {
           int n = depth.length;
                
           int[][] cost = new int[n + 1][n + 1];
           int[][] choice = new int[n + 1][n + 1];
           
           for(int i = 1; i <= n; i++){
               cost[i][i] = depth[i - 1];
               choice[i][i] = i;
           }
           
           for(int len = 1; len < n ; len++){
               for(int i = 1; i <= n - len; i++){
                   int j = i + len;
                
                   cost[i][j] = Integer.MAX_VALUE;
                   
                   for(int k = i; k <= j; k++){
                       int left = k > i ? cost[i][k - 1] : 0;
                       int right = k < j ? cost[k + 1][j] : 0;
                       int total = depth[k - 1] + Math.max(left, right);
                       
                       if(total < cost[i][j]){
                           cost[i][j] = total;
                           choice[i][j] = k;
                       }
                   }
               }
           }
           
           int start = 1;
           int end = n;
           
           while(start <= end){
               int k = choice[start][end];
               int result = excavate.apply(k);
               
               if(result == 0) return k;
               else if(result == 1) start = k + 1;
               else end = k - 1;
           }
           
           return - 1;
       }
    }
}
