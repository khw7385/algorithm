package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12865 {
    private static int N, K;
    private static Item[] items;
    private static int[][] dp;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1][N + 1];
        items = new Item[N + 1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    private static void solve(){
        for(int i = 1; i <= K; i++){ // 무게
            for(int j = 1; j <= N; j++){ // 아이템 인덱스
                if(i >= items[j].weight) dp[i][j] = Math.max(dp[i][j - 1], dp[i - items[j].weight][j - 1] + items[j].value);
                else dp[i][j] = dp[i][j - 1];
            }
        }

        System.out.println(dp[K][N]);
    }

    private static class Item{
        private int weight;
        private int value;

        public Item(int weight, int value){
            this.weight = weight;
            this.value = value;
        }
    }
}
