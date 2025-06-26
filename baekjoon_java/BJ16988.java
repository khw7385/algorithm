package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ16988 {
    private static int n, m;
    private static int[][] map;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solve(){
        int result = 0;

        for(int i = 0; i < n * m ; i++){
            int iCol = i / m; int iRow = i % m;
            if(map[iCol][iRow] != 0) continue;

            for(int j = i + 1; j < n * m; j++){
                int jCol = j / m; int jRow = j % m;
                if(map[jCol][jRow] != 0) continue;

                map[iCol][iRow] = 1;
                map[jCol][jRow] = 1;
                int deadStone = calcMaxDeadOpponentStone(iCol, iRow, jCol, jRow);
                map[iCol][iRow] = 0;
                map[jCol][jRow] = 0;

                result = Math.max(result, deadStone);
            }
        }
        System.out.println(result);
    }

    private static int calcMaxDeadOpponentStone(int col1, int row1, int col2, int row2){
        // init
        boolean[][] isImpossible = new boolean[n][m];
        boolean[][] visited = new boolean[n][m];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        int result = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j] || map[i][j] != 2) continue;
                
                Queue<Point> queue = new ArrayDeque<>();
                queue.add(new Point(i, j));
                visited[i][j] = true;
                int cnt = 1;
                boolean isSurrounded = true;

                while(!queue.isEmpty()){
                    Point curPoint = queue.poll();
                    
                    for(int k = 0; k < 4; k++){
                        int nextX = curPoint.x + dx[k];
                        int nextY = curPoint.y + dy[k];

                        if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;

                        if(isImpossible[nextX][nextY] || map[nextX][nextY] == 0){
                            isImpossible[i][j] = true;
                            isImpossible[curPoint.x][curPoint.y] = true;
                            while(!queue.isEmpty()){
                                Point p = queue.poll();
                                isImpossible[p.x][p.y] = true;
                            }
                            isSurrounded = false;
                            cnt = 0;
                            break;
                        }

                        if(visited[nextX][nextY]) continue;

                        if(map[nextX][nextY] == 2){
                            visited[nextX][nextY] = true;
                            cnt++;
                            queue.add(new Point(nextX, nextY));
                        }
                    }
                    if(!isSurrounded) break;
                }
                result += cnt;
            }
        }
        return result;
    }

    private static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
