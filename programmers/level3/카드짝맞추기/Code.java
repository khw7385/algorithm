package programmers.level3.카드짝맞추기;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board = {
                { 1, 0, 0, 3 },
                { 2, 0, 0, 0 },
                { 0, 0, 0, 2 },
                { 3, 0, 1, 0 }
        };
        int r = 1;
        int c = 0;
        int result = solution.solution(board, r, c);

        System.out.println(result);
    }

    private static class Solution {
        private Map<Integer, CardPair> cardPosMap = new HashMap<>();

        int[] dx = { 1, 0, -1, 0 };
        int[] dy = { 0, 1, 0, -1 };

        public int solution(int[][] board, int r, int c) {

            List<Integer> cards = new ArrayList<>();

            for (int i = 1; i <= 6; i++) {
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        if (board[x][y] == i) {
                            if (cardPosMap.containsKey(i)) {
                                CardPair cp = cardPosMap.get(i);
                                Point point = new Point(x, y, 0);
                                cp.second = point;
                                cards.add(i);
                                break;
                            }

                            CardPair cp = new CardPair();
                            Point point = new Point(x, y, 0);
                            cp.first = point;

                            cardPosMap.put(i, cp);
                        }
                    }
                }
            }

            return perm(board, cards, new Point(r, c, 0), 0);
        }

        private int perm(int[][] board, List<Integer> cards, Point start, int step) {
            if (step == cards.size()) {
                return 0;
            }
            int res = Integer.MAX_VALUE;

            for (int i = 0; i < cards.size(); i++) {
                if (cards.get(i) == -1)
                    continue;

                int cardNum = cards.get(i);
                CardPair cp = cardPosMap.get(cardNum);

                Point first = cp.first;
                Point second = cp.second;

                int distA = bfs(board, start, first) + bfs(board, first, second) + 2;
                int distB = bfs(board, start, second) + bfs(board, second, first) + 2;

                // 백트리캥
                board[first.x][first.y] = 0;
                board[second.x][second.y] = 0;
                cards.set(i, -1);

                // 재귀를 이용한 순열
                res = Math.min(res, distA + perm(board, cards, second, step + 1));
                res = Math.min(res, distB + perm(board, cards, first, step + 1));

                // 복구
                cards.set(i, cardNum);
                board[first.x][first.y] = cardNum;
                board[second.x][second.y] = cardNum;
            }

            return res;
        }

        private int bfs(int[][] board, Point start, Point end) {
            if (start.x == end.x && start.y == end.y)
                return 0;
            Queue<Point> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[4][4];

            queue.add(start);
            visited[start.x][start.y] = true;

            while (!queue.isEmpty()) {
                Point curPos = queue.poll();
                if (curPos.x == end.x && curPos.y == end.y)
                    return curPos.move;

                for (int i = 0; i < 4; i++) {
                    int nextX = curPos.x + dx[i];
                    int nextY = curPos.y + dy[i];

                    // 1칸 이동
                    if (isRange(nextX, nextY) && !visited[nextX][nextY]) {
                        visited[nextX][nextY] = true;
                        queue.add(new Point(nextX, nextY, curPos.move + 1));
                    }

                    // ctrl 이동
                    Point ctrlPos = getCtrlPos(board, curPos.x, curPos.y, i);
                    if (!visited[ctrlPos.x][ctrlPos.y]) {
                        visited[ctrlPos.x][ctrlPos.y] = true;
                        queue.add(new Point(ctrlPos.x, ctrlPos.y, curPos.move + 1));
                    }
                }
            }

            return 0;
        }

        private boolean isRange(int x, int y) {
            return x >= 0 && x < 4 && y >= 0 && y < 4;
        }

        Point getCtrlPos(int[][] board, int x, int y, int dir) {
            while (true) {
                x += dx[dir];
                y += dy[dir];

                if (!isRange(x, y))
                    return new Point(x - dx[dir], y - dy[dir], 0);
                if (board[x][y] != 0)
                    return new Point(x, y, 0);
            }
        }

        class Point {
            int x;
            int y;
            int move;

            Point(int x, int y, int move) {
                this.x = x;
                this.y = y;
                this.move = move;
            }
        }

        class CardPair {
            Point first, second;
        }
    }
}
