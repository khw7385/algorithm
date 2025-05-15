package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1182{
    private static int N, S;
    private static int[] arr;
    private static int result = 0;
    public static void main(String[] args)throws IOException{
        readInput();
        solve();
    }

    private static void readInput()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve(){
        Arrays.sort(arr);
        backtracking(0, 0);

        System.out.println(result);
    }

    private static void backtracking(int idx, int sum){
        for(int i = idx; i < N; i++){
            if(sum + arr[i] == S) result++;
            backtracking(i + 1, sum + arr[i]);
        }
    }
}