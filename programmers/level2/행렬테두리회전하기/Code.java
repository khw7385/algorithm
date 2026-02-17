package programmers.level2.행렬테두리회전하기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code {

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {
                { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 }
        };

        int[] result = new Solution().solution(rows, columns, queries);
        System.out.println(Arrays.toString(result));
    }

    private static class Solution {
        private int[][] matrix;

        private int[] dx = { 0, 1, 0, -1 };
        private int[] dy = { -1, 0, 1, 0 };

        public int[] solution(int rows, int columns, int[][] queries) {
            init(rows, columns, queries);

            List<Integer> answer = new ArrayList<>();

            for (int[] query : queries) {
                int x1 = query[0], y1 = query[1];
                int x2 = query[2], y2 = query[3];

                answer.add(rotate(x1, y1, x2, y2));
            }

            return answer.stream().mapToInt(i -> i).toArray();
        }

        void init(int rows, int columns, int[][] queries) {
            matrix = new int[rows][columns];

            int num = 1;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    matrix[i][j] = num++;
                }
            }
        }

        int rotate(int x1, int y1, int x2, int y2) {
            int d = 0;
            int x = x1, y = y1;
            int value = matrix[x - 1][y - 1];

            int min = value;

            while (true) {
                if (x + dx[d] < x1 || x + dx[d] > x2 || y + dy[d] < y1 || y + dy[d] > y2) {
                    d++;
                }

                if (d == 4)
                    break;

                int nextX = x + dx[d];
                int nextY = y + dy[d];
                int nextValue = matrix[nextX - 1][nextY - 1];

                matrix[nextX - 1][nextY - 1] = value;

                x = nextX;
                y = nextY;
                value = nextValue;
                min = Math.min(min, value);
            }

            return min;
        }
    }
}
