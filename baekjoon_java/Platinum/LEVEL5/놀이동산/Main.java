package baekjoon_java.Platinum.LEVEL5.놀이동산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int taxiFare = Integer.parseInt(st.nextToken());
        int busFare = Integer.parseInt(st.nextToken());

        int[] people = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        long result = solve(n, taxiFare, busFare, people);

        System.out.println(result);
    }

    private static long solve(int n, int taxiFare, int busFare, int[] inputPeople) {
        Arrays.sort(inputPeople);

        int[] people = new int[n + 1];
        long[] sum = new long[n + 1];
        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            people[i] = inputPeople[i - 1];
            sum[i] = sum[i - 1] + people[i];
        }

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + taxiFare * people[i];

            for (int j = i; j >= 1 && (i - j + 1) <= 40; j--) {
                int midIdx = (i + j) / 2;
                int median = people[midIdx];

                int leftCount = midIdx - j + 1;
                long leftSum = (leftCount * median) - (sum[midIdx] - sum[j - 1]);

                int rightCount = i - midIdx;
                long rightSum = (sum[i] - sum[midIdx]) - rightCount * median;

                long cost = (leftSum + rightSum) * taxiFare;

                dp[i] = Math.min(dp[i], dp[j - 1] + cost + busFare);
            }
        }

        return dp[n - 1];
    }
}
