package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18808 {
    private static int N, M, K;
    private static int[][] map;
    private static Sticker[] stickers;
    private static int result = 0;

    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }
    
    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        stickers = new Sticker[K];
    
        for(int i = 0; i < K; i++){
            int row, col;

            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());

            Sticker sticker = new Sticker(row, col);

            for(int j = 0; j < row; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < col; k++){
                    sticker.set(j, k, Integer.parseInt(st.nextToken()));
                }
            }

            stickers[i] = sticker;
        }
    }

    private static void solve(){
        for(Sticker sticker: stickers){
            putSticker(sticker);
        }
        System.out.println(result);
    }

    private static void putSticker(Sticker sticker){
        for(int dir = 0; dir < 4; dir++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(canExceedBoundary(sticker, i, j)) continue;
                    if(putStickerOnNotebook(sticker, i, j)) return;
                }
            }
            sticker.rotate();
        }
    }
    private static boolean canExceedBoundary(Sticker sticker, int x, int y){
        return sticker.getColLength() > N - x || sticker.getRowLength() > M - y; 
    }

    private static boolean putStickerOnNotebook(Sticker sticker, int x, int y){
        for(int i = 0; i < sticker.getColLength(); i++){
            for(int j = 0; j < sticker.getRowLength(); j++){
                if(map[x + i][y + j] == 0) continue;
                if(map[x + i][y + j] == 1 && sticker.get(i, j) == 1) return false;
            }
        }
        for(int i = 0; i < sticker.getColLength(); i++){
            for(int j = 0; j < sticker.getRowLength(); j++){
                if(sticker.get(i, j) == 1){
                    map[x + i][y + j] = 1;
                    result++;
                }
            }
        }
        return true;
    }
    
    private static class Sticker{
        private int[][] arr;

        public Sticker(int row, int col){
            arr = new int[row][col];
        }

        public void set(int x, int y, int val){
            arr[x][y] = val;
        }
        
        public int get(int x, int y){
            return this.arr[x][y];
        }

        public void rotate(){
            int colLength = arr.length;
            int rowLength = arr[0].length;

            int[][] temp = new int[rowLength][colLength];

            for(int i = 0; i < rowLength; i++){
                for(int j = 0; j < colLength; j++){
                    temp[i][j] = arr[colLength - 1 - j][i];
                }
            }
            arr = temp;
        }

        public int getColLength(){
            return this.arr.length;
        }

        public int getRowLength(){
            return this.arr[0].length;
        }
    }
}
