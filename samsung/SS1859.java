package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SS1859{
    private static int testCase, n;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine());

        for(int i = 1; i <= testCase; i++){
            n = Integer.parseInt(br.readLine());
            arr = new int[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            solve(i);
        }
    }

    private static void solve(int testIdx){
        Long result = 0L;

        int max = 0;
        for(int i = n - 1; i >= 0; i--){
            if(arr[i] > max) max = arr[i];
            result += max - arr[i];
        }

        System.out.printf("#%d %d\n", testIdx, result);
    }
}