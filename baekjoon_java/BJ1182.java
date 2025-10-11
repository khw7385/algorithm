package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1182 {
    private static int N, S;
    private static int[] arr;

    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            
            for(int i = 0 ; i < N; i++){
                arr[i] = Integer.parseInt(st.nextToken());    
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solve(){
        int count = 0;
        Arrays.sort(arr);

        for(int status = 1; status < (1 << N); status++){
            int temp = status;

            int sum = 0;
            boolean isCase = true;
            
            for(int i = 0 ; i < N; i++){
                boolean used = temp % 2 == 1;
                temp = temp / 2;
                
                if(!used) continue;
                if(pruning(arr[i], sum)){
                    isCase = false;
                    break;
                } 

                sum += arr[i];
                
            }

            if(isCase && sum == S) count++;
        }

        return count;
    }

    private static boolean pruning(int num, int sum){
        if(sum >= S && num > 0) return true;
        return false;
    }
}
