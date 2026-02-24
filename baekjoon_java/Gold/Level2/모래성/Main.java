package baekjoon_java.Gold.Level2.모래성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int h, w;
    private static int[][] sandCastle;
    private static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        sandCastle = new int[h][w];

        for (int i = 0; i < h; i++) {
            String row = br.readLine();

            for (int j = 0; j < w; j++) {
                if (row.charAt(j) == '.') {
                    sandCastle[i][j] = 0;
                    queue.add(new int[] { i, j });
                } else {
                    sandCastle[i][j] = row.charAt(j) - '0';
                }
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        int[] dx = { 1, 1, 1, 0, 0, -1, -1, -1 };
        int[] dy = { 1, 0, -1, 1, -1, 1, 0, -1 };

        int waves = 0;

        while (!queue.isEmpty()) {
            boolean changed = false;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();

                for (int j = 0; j < 8; j++) {
                    int nx = curr[0] + dx[j];
                    int ny = curr[1] + dy[j];

                    if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                        continue;

                    if (sandCastle[nx][ny] != 0) {
                        sandCastle[nx][ny]--;

                        if (sandCastle[nx][ny] == 0) {
                            changed = true;
                            queue.add(new int[] { nx, ny });
                        }
                    }
                }
            }

            if (changed)
                waves++;

        }

        return waves;
    }
}
