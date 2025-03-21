package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ15486 {
    private static int n;
    private static Consult[] consults;
    private static int[] dp;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        consults = new Consult[n + 1];
        dp = new int[n + 1];

        for(int i = n; i > 0; i--){
            String[] consult = br.readLine().split(" ");

            int day = Integer.parseInt(consult[0]);
            int pay = Integer.parseInt(consult[1]);

            consults[i] = new Consult(day, pay);
        }
    }

    private static void solve(){
    
        for(int i = 1; i <= n; i++){
            if(i - consults[i].day < 0) dp[i] = dp[i - 1];
            else dp[i] = Math.max(dp[i - 1], dp[i - consults[i].day] + consults[i].pay);
        }

        System.out.println(dp[n]);
    }

    static class Consult{
        int day;
        int pay;

        public Consult(int day, int pay){
            this.day = day;
            this.pay = pay;
        }
    }
}
