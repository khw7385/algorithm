package programmers.level2.삼각달팽이;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(6);
        System.out.println(Arrays.toString(result));
    }

    static class Solution {
        private int[][] matrix;

        public int[] solution(int n) {
            init(n);
            int[] answer = new int[n * (n + 1) / 2];

            int x = -1, y = 0;
            int num = 1;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (i % 3 == 0) {
                        x++;
                    } else if (i % 3 == 1) {
                        y++;
                    } else if (i % 3 == 2) {
                        x--;
                        y--;
                    }
                    matrix[x][y] = num++;
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    answer[i * (i + 1) / 2 + j] = matrix[i][j];
                }
            }
            return answer;
        }

        private void init(int n) {
            matrix = new int[n][n];
        }
    }
}