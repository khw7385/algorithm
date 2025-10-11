package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BJ2295 {
    private static int n;
    private static int[] arr;
    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            n = Integer.parseInt(br.readLine());

            arr = new int[n];
            for(int i = 0 ; i < n; i++){
                arr[i] = Integer.parseInt(br.readLine());
            }   
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solve(){
        Arrays.sort(arr);
        List<Integer> two = new ArrayList<>();

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n; j++){
                two.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(two);

        int result = -1;

        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < n; j++){
                result = Collections.binarySearch(two, arr[i] - arr[j]);
                if(result >= 0){
                    return arr[i];
                }
            }
        }

        return result;
    }
}
