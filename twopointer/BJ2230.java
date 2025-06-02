package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2230{
    private static int n;
    private static int m;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solve(){
        // 정렬
        Arrays.sort(arr);

        int start = 0;
        int end = 0;
        int min = Integer.MAX_VALUE;

        while(start <= end){
            // 종료조건
            if(end >= n || start >= n) break;

            int diff = arr[end] - arr[start];

            if(diff < m) end++;
            else{
                min = diff < min ? diff : min;
                start++; 
            }
        }

        System.out.println(min);
    }
}