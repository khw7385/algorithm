package programmers.level2.리프노드수최대화;

public class Code {
    
    public static void main(String[] args) {
        Solution solution = new Solution();

        int dist_limit = 0;
        int split_limit = 10;

        int result = solution.solution(dist_limit, split_limit);
        System.out.println(result);
    }

    static class Solution {
        private long answer = 1;
    
        public int solution(int dist_limit, int split_limit) {
            answer = 1;
            dfs(dist_limit, split_limit, 0L, 1L, 1L, 0L);
            return (int)answer;
        }
    
        private void dfs(long distLimit, long splitLimit,
                         long distUsed, long levelNodes, long product, long accumulated) {
            answer = Math.max(answer, accumulated + levelNodes);
    
            for (int c = 2; c <= 3; c++) {
                long nextProduct = product * c;
                if (nextProduct > splitLimit) break;
    
                long maxK = Math.min(levelNodes, distLimit - distUsed);
                if (maxK <= 0) break;
    
                dfs(distLimit, splitLimit,
                    distUsed + maxK,
                    maxK * c,
                    nextProduct,
                    accumulated + (levelNodes - maxK));
            }
        }
    }
}
