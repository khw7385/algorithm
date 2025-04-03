package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BJ11559{
    private static char[][] map = new char[12][6];
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 12; i++){
            String col = br.readLine();

            for(int j = 0; j < 6; j++){
                map[i][j] = col.charAt(j);
            }
        }
    }

    private static void solve(){        
        boolean chainFlag = true;

        int result = 0;
        while(true){
            if(chainFlag == false){
                break;
            }

            chainFlag = false;

            for(int i = 0; i < 12; i++){
                for(int j = 0; j < 6; j++){
                    if(map[i][j] != '.' && map[i][j] != 'X'){
                        List<Point> group = new ArrayList<>();
                        bfs(i, j, group);

                        if(group.size() >= 4){
                            chainFlag = true;
                            removeGroupItemInMap(group);
                        }
                    } 
                }
            }
            if(chainFlag){
                drop();
                result++;
            }
        }

        System.out.println(result);
    }

    private static void bfs(int x, int y, List<Point> group){
        boolean[][] visited= new boolean[12][6];
        Queue<Point> queue = new ArrayDeque<>();
        
        queue.add(new Point(x, y));
        group.add(new Point(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()){
            Point p = queue.poll();

            for(int d = 0; d < 4; d++){
                int nextX = p.x + dx[d];
                int nextY = p.y + dy[d];

                // 범위를 벗어나는 경우
                if(nextX < 0 || nextX >= 12 || nextY < 0 || nextY >= 6){
                    continue;
                }

                // 방문한 적이 있는 경우
                if(visited[nextX][nextY]){
                    continue;
                }
                
                // 같은 색깔이 있는 경우
                if(map[nextX][nextY] == map[x][y]){
                    queue.add(new Point(nextX, nextY));
                    group.add(new Point(nextX, nextY));
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    private static void removeGroupItemInMap(List<Point> group){
        for(Point p: group){
            map[p.x][p.y] = 'X';
        }
    }

    private static void drop(){
        for(int i = 0; i < 6; i++){
            int move = 0;
            
            if(map[11][i] == 'X'){
                move = 1;
            }

            for(int j = 10; j >= 0; j--){
                if(map[j][i] == 'X'){
                    move++;
                    continue;
                }
                map[j + move][i] = map[j][i];
            }

            for(int j = 0 ; j < move; j++){
                map[j][i] = '.';
            }
        }
    }

    // 해당 칸이 X 

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}