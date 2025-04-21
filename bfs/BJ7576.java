package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ7576{
    private static int n, m;
    private static int[][] board;
    private static int[][] date;
    private static boolean[][] visited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1 ,0, -1};
    private static Queue<Point> queue = new ArrayDeque<>();
    
    public static void main(String[] args) throws IOException {
       readInput();
       solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] boardInfo = br.readLine().split(" ");
        m = Integer.parseInt(boardInfo[0]);
        n = Integer.parseInt(boardInfo[1]);

        board = new int[n][m];
        visited = new boolean[n][m];
        date = new int[n][m];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                
                if(board[i][j] == 1){
                    queue.add(new Point(i ,j));
                    visited[i][j] = true;
                }
            }
        }
    }

    private static void solve(){
        // BFS
        while(!queue.isEmpty()){
            Point cur = queue.poll();

            for(int i = 0; i < 4;i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny] || board[nx][ny] == -1) continue;

                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;
                date[nx][ny] = date[cur.x][cur.y] + 1;
            }
        }

        int max = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m; j++){
                if(date[i][j] == 0 && board[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
                max = Math.max(max, date[i][j]);
            }
        }

        System.out.println(max);
    }

    private static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}