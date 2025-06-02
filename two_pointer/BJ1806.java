package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1806 {
    private static int n, s;
    private static int[] arr;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve(){
        int[] sum = new int[n + 1];

        sum[0] = arr[0];
        for(int i = 1; i <= n; i++){
            sum[i] = sum[i - 1] + arr[i];
        }

        int start = 0;
        int end = 0;
        int minLength = 100000;

        while(start <= end){
            // 종료조건
            if(end > n || start > n) break;

            int sectionSum = sum[end] - sum[start];

            if(sectionSum < s) end++;
            else{
                int length = end - start;
                minLength = Math.min(length, minLength);
                start++;
            }
        }
        if(minLength == 100000){
            System.out.println(0);
            return;
        }

        System.out.println(minLength);
    }
}
