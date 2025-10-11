package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9663 {
    private static int n;
    private static boolean[] isUsed1;
    private static boolean[] isUsed2;
    private static boolean[] isUsed3;

    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            n = Integer.parseInt(br.readLine());
            isUsed1 = new boolean[n];
            isUsed2 = new boolean[2 * n - 1];
            isUsed3 = new boolean[2 * n - 1];
        }catch(IOException e){
            System.out.println(e.getMessage());
        }        
    }

    private static int solve(){
        return backtracking(0);
    }

    private static int backtracking(int floor){
        if(floor == n) return 1;

        int count = 0;
        for(int i = 0 ; i < n; i++){
            if(isUsed1[i] || isUsed2[floor + i] || isUsed3[floor + n - i - 1]) continue;

            isUsed1[i] = true;
            isUsed2[floor + i] = true;
            isUsed3[floor + n - i - 1] = true;

            count += backtracking(floor + 1);

            isUsed1[i] = false;
            isUsed2[floor + i] = false;
            isUsed3[floor + n - i - 1] = false;
        }

        return count;
    }
}
