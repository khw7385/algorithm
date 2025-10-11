package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ2178
{
    private static int n, m;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            String[] size = br.readLine().split(" ");

            n = Integer.parseInt(size[0]);
            m = Integer.parseInt(size[1]);

            arr = new int[n][m];
            visited = new boolean[n][m];

            for(int i = 0; i < n; i++){
                String line = br.readLine();
                for(int j = 0; j < m; j++){
                    arr[i][j] = line.charAt(j) - '0';                    
                }
            }
        }catch(IOException e){
        }
    }

    private static int solve(){
        Queue<Point> queue = new ArrayDeque<>();
        
        queue.add(new Point(0, 0, 1));
        visited[0][0] = true;
        int result = 0;

        while(!queue.isEmpty()){
            Point now = queue.poll();
            
            if(now.x == n - 1 && now.y == m - 1){
                result = now.pass;
                break;
            } 

            for(int i = 0; i < 4; i++){
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];
                int nextPass = now.pass + 1;

                if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                if(visited[nextX][nextY]) continue;
                if(arr[nextX][nextY] == 0) continue;

                queue.add(new Point(nextX, nextY, nextPass));
                visited[nextX][nextY] = true;
            }
        }

        return result;
    }

    static class Point{
        int x;
        int y;
        int pass;

        Point(int x, int y, int pass){
            this.x = x;
            this.y = y;
            this.pass = pass;
        }
    }
}

