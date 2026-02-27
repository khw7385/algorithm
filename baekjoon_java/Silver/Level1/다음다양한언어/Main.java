package baekjoon_java.Silver.Level1.다음다양한언어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        String result = solve(input);

        if (result.equals("-1")) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static String solve(String str) {
        boolean[] alphas = new boolean[26];

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            alphas[ch - 'a'] = true;
        }

        for (int i = 0; i < 26; i++) {
            if (!alphas[i]) {
                return str + (char) ('a' + i);
            }
        }

        char prev = str.charAt(str.length() - 1);

        for (int i = str.length() - 2; i >= 0; i--) {
            char now = str.charAt(i);

            if (now < prev) {
                char next = prev;

                for (int j = i + 2; j < str.length(); j++) {
                    if (now < str.charAt(j)) {
                        if (str.charAt(j) < next)
                            next = str.charAt(j);
                    }
                }

                return str.substring(0, i) + next;
            }

            prev = now;
        }

        return "-1";
    }
}
