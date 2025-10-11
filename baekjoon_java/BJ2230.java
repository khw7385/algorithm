package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ2230 {
    private static int n, m;
    private static int[] arr;
    public static void main(String[] args) {
        init();
        int answer = solveUsingTwoPointer();
        System.out.println(answer);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = Integer.parseInt(br.readLine());
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solveUsingTwoPointer(){
        Arrays.sort(arr);

        int pointer1 = 0, pointer2 = 0;

        int min = Integer.MAX_VALUE;

        while(pointer1 <= pointer2){
            if(pointer2 >= n) break;

            int diff = arr[pointer2] - arr[pointer1];

            if(diff >= m){
                if(diff == m) return diff;
                pointer1++;
                min = Math.min(min, diff);
            }else{
                pointer2++;
            }
        }
        
        return min;
    }

    private static int solveUsingBinarySearch(){
        Arrays.sort(arr);
        
        return 0;
    }
}
