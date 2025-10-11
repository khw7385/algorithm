package programmers.level4.가장큰삼각형덩어리;

import java.util.ArrayDeque;
import java.util.Queue;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] grid = {
            { -1, -1, -1},
            { 1, 1, -1},
            { 1, 1, 1}
        };
        
        int answer = solution.solution(grid);
        System.out.println(answer);
    }
    
    static class Solution{
        private int n, m;
        private int[][] grid;
        private int[][][] groups;

        private int[] dx = { -1, 1, 0, 0};
        private int[] dy = { 0, 0, -1, 1};
        private int[][] dir = {{0, 3}, {1, 2}, {0, 2}, {1, 3}};

        public int solution(int[][] grid) {
            init(grid);

            int result = 0;
            int group = 1;

            for(int i = 0 ; i < n; i++){
                for(int j = 0 ; j < m; j++){
                    for(int k = 0; k < 2; k++){
                        if(groups[i][j][k] != 0) continue;
                        result = Math.max(result, countTriangle(i, j, k, group));
                        group++;
                    }                    
                }
            }

            return result;
        }

        private void init(int[][] grid){
            this.n = grid.length;
            this.m = grid[0].length;

            this.grid = new int[n][m];
            for(int i = 0 ; i < n; i++){
                for(int j = 0; j < m; j++){
                    this.grid[i][j] = grid[i][j];
                }
            }

            this.groups = new int[n][m][2];
        }

        private int countTriangle(int x, int y, int state, int group){
            Queue<Triangle> queue = new ArrayDeque<>();
            queue.add(new Triangle(x, y, state));
            
            groups[x][y][state] = group;
            int count = 0;

            while(!queue.isEmpty()){
                Triangle triangle = queue.poll();
                count++;

                int shape;
                if(grid[triangle.x][triangle.y] == -1) shape = triangle.state == 0 ? 0 : 1;
                else shape = triangle.state == 0 ? 2: 3;

                for(int i = 0 ; i < 2; i++){
                    int nd = dir[shape][i];
                    int nextX = triangle.x + dx[nd];
                    int nextY = triangle.y + dy[nd];

                    if(nextX < 0 || nextY < 0 || nextX >= n || nextY >= m) continue;                    
                    if(groups[nextX][nextY][0] == group || groups[nextX][nextY][1] == group) continue;

                    int nextState;
                    if(nd == 0 || nd == 1) nextState = 1 - nd;
                    else{
                        if(nd == 2) nextState = grid[nextX][nextY] == -1? 0 : 1;
                        else nextState = grid[nextX][nextY] == -1? 1 :0;
                    }

                    groups[nextX][nextY][nextState] = group;
                    queue.add(new Triangle(nextX, nextY, nextState));
                }
            }

            return count;
        }


        static class Triangle{
            int x;
            int y;
            int state;

            Triangle(int x, int y, int state){
                this.x = x;
                this.y = y;
                this.state = state;
            }
        }
    }
}
