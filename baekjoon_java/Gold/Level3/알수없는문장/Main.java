package baekjoon_java.Gold.Level3.알수없는문장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static String sentence;
    private static int n;
    private static String[] words;
    private static int[] dp;
    private static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sentence = br.readLine();
        n = Integer.parseInt(br.readLine());
        words = new String[n];

        dp = new int[sentence.length()];
        Arrays.fill(dp, -2);

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        System.out.println(solve(0));
    }

    private static int solve(int start) {
        if (start == sentence.length())
            return 0;
        if (dp[start] != -2)
            return dp[start];

        int minCost = INF;

        for (String word : words) {
            int length = word.length();

            if (start + length > sentence.length())
                continue;

            String sub = sentence.substring(start, start + length);

            if (isPossible(sub, word)) {
                int cost = getCost(sub, word);
                int res = solve(start + length);

                if (res == -1)
                    continue;
                minCost = Math.min(minCost, cost + res);
            }
        }

        return dp[start] = minCost == INF ? -1 : minCost;
    }

    private static boolean isPossible(String s1, String s2) {
        int[] alphas = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            alphas[s1.charAt(i) - 'a']++;
            alphas[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (alphas[i] != 0)
                return false;
        }

        return true;
    }

    private static int getCost(String s1, String s2) {
        int cost = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i))
                cost++;
        }

        return cost;
    }
}
