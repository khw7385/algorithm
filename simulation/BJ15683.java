package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15683 {
    private static int N, M;
    private static int[][] map, copyMap;
    private static List<Point> startPoints = new ArrayList<>();

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int result = 0;

    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }
    
    private static void readInput()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        copyMap = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){
                    startPoints.add(new Point(i, j));
                }
            }
        }
    }

    private static void solve(){
        for(int status = 0; status  <  (1 << (2 * startPoints.size())); status++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    copyMap[i][j] = map[i][j];
                }
            }
            int temp = status;

            for(int i = 0; i < startPoints.size(); i++){
                int dir = temp % 4; 
                temp /= 4;
                Point p = startPoints.get(i);

                if(map[p.x][p.y] == 1){
                    light(p.x, p.y, dir);
                }
                else if(map[p.x][p.y] == 2){
                    light(p.x, p.y, dir);
                    light(p.x, p.y, (dir + 2) % 4);
                }
                else if(map[p.x][p.y] == 3){
                    light(p.x, p.y, dir);
                    light(p.x, p.y, (dir + 1) % 4);
                }
                else if(map[p.x][p.y] == 4){
                    light(p.x, p.y, dir);
                    light(p.x, p.y, (dir + 1) % 4);
                    light(p.x, p.y, (dir + 2) % 4);
                }
                else if(map[p.x][p.y] == 5){
                    light(p.x, p.y, 0);
                    light(p.x, p.y, 1);
                    light(p.x, p.y, 2);
                    light(p.x, p.y, 3);
                }
            }

            int cnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(copyMap[i][j] != 0) cnt++;
                }
            }

            result = Math.max(result, cnt);
        }

        System.out.println(N * M - result);
    }

    private static void light(int x, int y, int d){
        int nx = x;
        int ny = y;
        while(true){
            nx = nx + dx[d];
            ny = ny + dy[d];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M)break;
            if(copyMap[nx][ny] == 6) break;
            copyMap[nx][ny] = 7;
        }
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
