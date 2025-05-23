package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SS1215 {
    private static char[][] arr = new char[8][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++){
            int length = Integer.parseInt(br.readLine());

            for(int j = 0; j < 8; j++){
                arr[j] = Arrays.copyOf(br.readLine().toCharArray(), 8);
            }

            solve(i, length);
        }
    }

    private static void solve(int testIdx, int length){
        int result = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                result += countPalindrome(i, j, length);
            }
        }

        System.out.println(String.format("#%d %d", testIdx + 1, result));
    }

    private static int countPalindrome(int x, int y, int length){
        int cnt = 0;
        // 오른쪽
        if(y + length - 1< 8){
            boolean flag = true;
            for(int i = 0; i < length / 2; i++){
                if(arr[x][y + i] != arr[x][y + length - 1 - i]){
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }


        // 아래쪽
        if(x + length - 1 < 8){
            boolean flag = true;
            for(int i = 0; i < length / 2; i++){
                if(arr[x + i][y] != arr[x + length - 1 - i][y]){
                    flag = false;
                    break;
                }
            }
            if(flag) cnt++;
        }

        return cnt;
    }
}
