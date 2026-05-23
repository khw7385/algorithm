package samsung.level4.격자판의숫자이어붙이기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Code {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());
        int[][] grid = new int[4][4];

        for (int t = 1; t <= test; t++) {
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = new Solution().solution(grid);
            // test 대신 t를 출력하도록 수정
            System.out.println(String.format("#%d %d", t, answer)); 
        }
    }

    static class Solution {
        // 중복을 자동으로 제거해주는 Set 사용 (배열 크기 고민 해결!)
        HashSet<Integer> numbers = new HashSet<>();

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        public int solution(int[][] grid) {
            // 매 테스트 케이스마다 중복을 저장할 set을 비워줌
            numbers.clear();

            // 모든 칸에서 각각 DFS 시작
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    // 1번째 자리 숫자, 현재 값, 현재 좌표 (isVisited는 제거)
                    dfs(grid, 1, grid[i][j], i, j);
                }
            }
            
            // Set의 크기가 곧 만들어진 서로 다른 숫자의 개수
            return numbers.size();
        }

        void dfs(int[][] grid, int length, int num, int curX, int curY) {
            // 7자리가 완성되면 Set에 넣고 종료 (중복은 알아서 제거됨)
            if (length == 7) {
                numbers.add(num);
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nextX = curX + dx[d];
                int nextY = curY + dy[d];

                if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4) continue;
                
                // 기존 숫자에 10을 곱하고 새 숫자를 더하는 표준 방식으로 변경
                dfs(grid, length + 1, num * 10 + grid[nextX][nextY], nextX, nextY);
            }
        }
    }
}