package baekjoon_java.Gold.Level4.호텔;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int c, n;
    private static List<City> cities = new ArrayList<>();

    private static final int INF = 987654321;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int client = Integer.parseInt(st.nextToken());
            cities.add(new City(cost, client));
        }

        solve();
    }
    private static void solve(){
        int[] dp = new int[c + 101];

        Arrays.fill(dp, INF);
        dp[0] = 0;
        for(int i = 0; i < n; i++){
            City city = cities.get(i);
            int cost = city.cost;
            int client = city.client;

            for(int j = client; j <= c + 100; j++){
                if(dp[j - client] != INF){
                    dp[j] = Math.min(dp[j], dp[j - client] + city.cost);
                }
            }
        }

        int answer = INF;
        for(int j = c; j <= c + 100; j++){
            answer = Math.min(answer, dp[j]);
        }
        System.out.println(answer);
    }

    static class City{
        int cost;
        int client;

        City(int cost, int client){
            this.cost = cost;
            this.client = client;
        }
    }
}
