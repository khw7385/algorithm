package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BJ4179 {
    private static int r, c;
    private static char[][] board;

    private static int jx, jy;
    private static List<Position> firePoints = new ArrayList<>();

    private static boolean[][] visited;

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args)throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] boardInfo = br.readLine().split(" ");

        r = Integer.parseInt(boardInfo[0]);
        c = Integer.parseInt(boardInfo[1]);

        board = new char[r][c];
        visited = new boolean[r][c];

        for(int i = 0; i < r; i++){
            String rowInfo = br.readLine();
            
            for(int j = 0; j < c; j++){
                board[i][j] = rowInfo.charAt(j);

                if(board[i][j] == 'J'){
                    jx = i;
                    jy = j;
                }
                else if(board[i][j] == 'F'){
                    firePoints.add(new Position(i, j, 'F', 0));
                }
            }
        }
    }

    private static void solve(){
        Queue<Position> queue = new ArrayDeque<>();

        // 초기화 과정
        if(!firePoints.isEmpty()){
            for(Position pos : firePoints){
                queue.add(pos);
            }
        }

        queue.add(new Position(jx, jy, 'J', 0));
        
        visited[jx][jy] = true;

        while(!queue.isEmpty()){
            Position cur = queue.poll();

            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // board 외부 체크
                if(nx < 0 || nx >= r || ny < 0 || ny >= c){
                    if(cur.target == 'J'){
                        System.out.println(cur.count + 1);
                        return;
                    }
                    continue;
                }

                // # 체크
                if(board[nx][ny] == '#' || board[nx][ny] == 'F') continue;

                if(cur.target == 'J'){
                    if(visited[nx][ny]) continue;
                    visited[nx][ny] = true;    
                }
                else{
                    board[nx][ny] = 'F';
                }
                queue.add(new Position(nx, ny, cur.target, cur.count + 1));
            }
        }

        System.out.println("IMPOSSIBLE");
    }


    static class Position{
        int x; 
        int y;
        char target;
        int count;

        Position(int x, int y, char target, int count){
            this.x = x; 
            this.y = y;
            this.target = target;
            this.count = count;
        }
    }
}
