package baekjoon_java.Platinum.LEVEL5.문자열압축하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String input;

    public static void main(String[] args) {
        readInput();
        int answer = solve(input);
        System.out.println(answer);
    }

    static void readInput() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();
        } catch (IOException e) {

        }
    }

    static int solve(String str) {
        int length = str.length();

        int[][] arr = new int[length][length];

        for (int len = 1; len <= length; len++) {
            for (int i = 0; i <= length - len; i++) {
                int j = i + len - 1;

                arr[i][j] = len;

                // 쪼개기
                for (int k = i; k < j; k++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k + 1][j]);
                }

                // 압축하기
                for (int subLen = 1; subLen <= len / 2; subLen++) {
                    if (len % subLen == 0) {
                        if (isRepeatable(str, i, j, subLen)) {
                            int compressed = String.valueOf(len / subLen).length() + arr[i][i + subLen - 1] + 2;
                            arr[i][j] = Math.min(arr[i][j], compressed);
                        }
                    }
                }
            }
        }

        int result = arr[0][str.length() - 1];

        return result;
    }

    private static boolean isRepeatable(String str, int start, int end, int subLen) {
        String pattern = str.substring(start, start + subLen);
        for (int k = start + subLen; k <= end; k += subLen) {
            if (!str.substring(k, k + subLen).equals(pattern)) {
                return false;
            }
        }
        return true;
    }
}