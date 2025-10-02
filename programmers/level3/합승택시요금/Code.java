package programmers.level3.합승택시요금;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 7;
        int s = 3;
        int a = 4;
        int b = 1;
        int[][] fares = {
            { 5, 7, 9 }, 
            { 4, 6, 4 }, 
            { 3, 6, 1 }, 
            { 3, 2, 3 }, 
            { 2, 1, 6 }
        };

        int answer = solution.solution(n, s, a, b, fares);

        System.out.println(answer);
    }

    private static class Solution{
        private static final long INF = Long.MAX_VALUE;

        private long[][] minDistance;
        
        public int solution(int n, int s, int a, int b, int[][] fares) {
            init(n, fares);
            floydWarshall(n);

            long result = INF;
            for(int i = 1; i <= n; i++){
                if(minDistance[s][i] == INF || minDistance[i][a] == INF || minDistance[i][b] == INF) continue;
            
                result = Math.min(result, minDistance[s][i] + minDistance[i][a] + minDistance[i][b]);
            }

            return (int) result;
        }

        private void init(int n, int[][] fares){
            this.minDistance = new long[n + 1][n + 1];

            for(int i = 1 ; i <= n; i++){
                Arrays.fill(minDistance[i], INF);
                minDistance[i][i] = 0;
            }

            for(int i = 0 ; i < fares.length; i++){
                int[] fare = fares[i];

                minDistance[fare[0]][fare[1]] = fare[2];
                minDistance[fare[1]][fare[0]] = fare[2];
            }
        }

        private void floydWarshall(int n){
            for(int mid = 1; mid <= n; mid++){
                for(int start = 1; start <= n; start++){
                    for(int end = 1; end <= n; end++){
                        if(minDistance[start][mid] == INF || minDistance[mid][end] == INF) continue;
                        minDistance[start][end] = Math.min(minDistance[start][end], minDistance[start][mid] + minDistance[mid][end]);
                    }
                }
            }
        }
    }
}
