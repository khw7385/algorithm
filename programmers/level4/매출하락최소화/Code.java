package programmers.level4.매출하락최소화;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] sales = { 10, 10, 1, 1 };
        int[][] links = {
                { 3, 2 },
                { 4, 3 },
                { 1, 4 },
        };

        int result = solution.solution(sales, links);
        System.out.println(result);
    }

    private static class Solution {
        private int[] sales;
        private Map<Integer, List<Integer>> edges = new HashMap<>();
        private int[][] dp;

        public int solution(int[] sales, int[][] links) {
            init(sales, links);

            treeDP(1);
            return Math.min(dp[1][0], dp[1][1]);
        }

        void init(int[] sales, int[][] links) {
            this.sales = Arrays.copyOf(sales, sales.length);

            for (int i = 1; i <= sales.length; i++) {
                edges.put(i, new ArrayList<>());
            }

            for (int[] link : links) {
                List<Integer> childNodes = edges.get(link[0]);
                childNodes.add(link[1]);
            }

            dp = new int[sales.length + 1][2];
        }

        void treeDP(int cur) {
            dp[cur][0] = 0;
            dp[cur][1] = sales[cur - 1]; // 자신 포함

            int minDiff = Integer.MAX_VALUE;
            boolean childSelected = false;

            for (int child : edges.get(cur)) {
                treeDP(child);

                dp[cur][1] += Math.min(dp[child][0], dp[child][1]);

                if (dp[child][0] < dp[child][1]) {
                    dp[cur][0] += dp[child][0];
                } else {
                    dp[cur][0] += dp[child][1];
                    childSelected = true;
                }

                minDiff = Math.min(minDiff, dp[child][1] - dp[child][0]);
            }

            if (!childSelected && !edges.get(cur).isEmpty()) {
                dp[cur][0] += minDiff;
            }
        }
    }
}
