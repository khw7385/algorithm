package baekjoon_java.Gold.Level2.합이0인네정수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        
        arr = new long[n][4];
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
        }

        solve();
    }

    private static void solve(){
        long[] sumAB = new long[n * n];
        long[] sumCD = new long[n * n];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                sumAB[n * i + j] = arr[i][0] + arr[j][1];
                sumCD[n * i + j] = arr[i][2] + arr[j][3];
            }
        }
        
        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        long result = 0;
        int left = 0;
        int right = n * n - 1;
    
        while(left < n * n && right >= 0){
            long sAB = sumAB[left];
            long sCD = sumCD[right];
            long total = sAB + sCD;

            if(total == 0){
                long countAB = 0;
                long countCD = 0;

                while(left < n * n && sAB == sumAB[left]){
                    countAB++;
                    left++;
                }

                while(right >= 0 && sCD == sumCD[right]){
                    countCD++;
                    right--;
                }

                result += countAB * countCD;
            }else if(total < 0){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(result);
    }
}
