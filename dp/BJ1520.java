package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1520 {
    private static int m, n;
    private static int[][] map;
    private static int result = 0;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        readInput();
        solve3();
    }

    private static void readInput(){
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            map = new int[m][n];
            visited = new boolean[m][n];
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }catch(IOException e){
        }
    }
    private static boolean[][] visited;
    private static void solve(){
        // dfs 를 이용한 방식 -> 시간 초과 발생!!
        visited[0][0] = true;
        dfs(0, 0);
        System.out.println(result);
    }
    private static void dfs(int x, int y){
        if(x == m - 1 && y == n - 1){
            result++;
            return;
        }

        for(int i = 0; i < 4;i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
            if(visited[nextX][nextY]) continue;
            if(map[x][y] <= map[nextX][nextY]) continue;

            visited[nextX][nextY] = true;
            dfs(nextX, nextY);
            visited[nextX][nextY] = false;
        }
    }

    private static void solve2(){
        int[][] dp = new int[m][n];

        List<Cell> cells = new ArrayList<>();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                cells.add(new Cell(i, j, map[i][j]));
            }
        }
        Collections.sort(cells, (a, b) -> b.h - a.h);

        dp[0][0] = 1;
        // 테이블을 채워가는 과정
        for(Cell cell: cells){
            int cnt = dp[cell.x][cell.y];
            if(cnt == 0) continue;

            for(int i = 0;i < 4;i++){
                int nextX = cell.x + dx[i];
                int nextY = cell.y + dy[i];

                if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
                if(map[nextX][nextY] >= map[cell.x][cell.y])
                dp[nextX][nextY] += dp[cell.x][cell.y]; // 점화식 
            }
        }

        System.out.println(dp[m - 1][n - 1]);
    }

    static class Cell{
        int x, y, h;
        public Cell(int x, int y, int h){
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    private static int[][] dp;
    // top-down 방식의 dp
    private static void solve3(){
        dp = new int[m][n];
        for(int i = 0; i < m; i++){
            Arrays.fill(dp[i], -1);
        }
        
        int result = dfsUsingDp(0, 0);
        System.out.println(result);
    }

    private static int dfsUsingDp(int x, int y){
        if(x == m - 1 && y == n - 1){
            dp[x][y] = 1;
            return dp[x][y];
        }
        if(dp[x][y] != -1){
            return dp[x][y];
        }

        int cnt  = 0;
        for(int i = 0; i < 4;i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) continue;
            if(map[x][y] <= map[nextX][nextY]) continue;
            cnt += dfsUsingDp(nextX, nextY);
        }
        dp[x][y] = cnt;
        return dp[x][y];
    }
}
