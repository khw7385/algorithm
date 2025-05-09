package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9663 {
    private static int N;
    private static boolean[] isUsed1;
    private static boolean[] isUsed2;
    private static boolean[] isUsed3;

    private static int result = 0;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        isUsed1 = new boolean[N];
        isUsed2 = new boolean[2 * N - 1];
        isUsed3 = new boolean[2 * N - 1];
    }

    private static void solve(){
        backtracking(0);
        System.out.println(result);
    }

    private static void backtracking(int n){
        if(n == N){
            result += 1;
            return;
        }

        for(int i = 0; i < N; i++){
            if(!isUsed1[i] && !isUsed2[i + n] && !isUsed3[n - i + N - 1]){
                isUsed1[i] = true;
                isUsed2[i + n] = true;
                isUsed3[n - i + N - 1] = true;
                backtracking(n + 1);
                isUsed1[i] = false;
                isUsed2[i + n] = false;
                isUsed3[n - i + N - 1] = false;
            }
        }
    }
}
