package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9084 {
    private static int tests;
    private static int[] coins;
    private static int target;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            tests = Integer.parseInt(br.readLine());

            for(int i = 0; i < tests; i++){
                int coinNum = Integer.parseInt(br.readLine());

                coins = new int[coinNum];
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < coinNum; j++){
                    coins[j] = Integer.parseInt(st.nextToken());
                }

                target = Integer.parseInt(br.readLine());

                int answer = solve();
                System.out.println(answer);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solve(){
        int[][] d = new int[coins.length][target + 1];
        
        for(int i = 0; i < coins.length; i++){
            d[i][0] = 1;
        }

        for(int i = 0; i < coins.length; i++){
            for(int j = 1; j <= target; j++){
                if(i - 1 >= 0) d[i][j] += d[i - 1][j];
                if(j - coins[i] >= 0) d[i][j] += d[i][j - coins[i]];
            }
        }

        return d[coins.length - 1][target];
    }
}
