package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2615 {
    private static int[][] badook = new int[19][19];
    private static boolean[][][] visited = new boolean[19][19][4];
    private static int[] dx = {1, 0, 1, 1};
    private static int[] dy = {0, 1, -1, 1};

    private static int[] result = new int[3];
    
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }   
    
    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 19; i++){
            String[] cellLine = br.readLine().split(" ");

            for(int j = 0; j < 19; j++){
                badook[i][j] = Integer.parseInt(cellLine[j]);
            }
        }
    }

    private static void solve(){
        for(int i = 0; i < 19; i++){
            for(int j = 0; j < 19; j++){
                if(badook[i][j] != 0){
                    for(int k = 0 ; k < 4; k++){
                        if(!visited[i][j][k]&& checkFive(i, j, k, badook[i][j], 1) == 5){
                            System.out.println(badook[i][j]);
                            if(k == 2){
                                System.out.println((i + 5) + " " + (j - 3));
                                return;
                            }
                            System.out.println((i + 1)+ " " + (j + 1));
                            return;
                        }   
                    }
                }    
            }
        }

        System.out.println(0);
    }

    private static int checkFive(int x, int y, int d, int color, int count){
        visited[x][y][d] = true;

        int nextX = x + dx[d];
        int nextY = y + dy[d];

        if(nextX < 0 || nextX >= 19 || nextY < 0 || nextY >= 19){
            return count;
        }

        if(badook[nextX][nextY] != color){
            return count;
        }

        return checkFive(nextX, nextY, d, color, count + 1);
    }
}
