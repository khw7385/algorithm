package baekjoon_java.Gold.Level3.앱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[] memories;
    private static int[] costs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        memories = new int[n];
        costs = new int[n];

        StringTokenizer stMemory = new StringTokenizer(br.readLine());
        StringTokenizer stCost = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            memories[i] = Integer.parseInt(stMemory.nextToken());
            costs[i] = Integer.parseInt(stCost.nextToken());
        }

        System.out.println(solve());
    }

    private static int solve() {
        int MAX_COST = 100 * 100;

        int[][] dp = new int[n + 1][MAX_COST + 1];

        for (int i = 1; i <= n; i++) {
            int curMemory = memories[i - 1];
            int curCost = costs[i - 1];

            for (int j = 0; j <= MAX_COST; j++) {
                if (j < curCost) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curCost] + curMemory);
                }
            }
        }

        for (int i = 0; i <= MAX_COST; i++) {
            if (dp[n][i] >= m) {
                return i;
            }
        }

        return -1;
    }
}
