package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SS5215{
    private static Ingredient[] ingredients;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());

        for(int i = 0; i < testNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int ingredientNum = Integer.parseInt(st.nextToken());
            int limitCalory = Integer.parseInt(st.nextToken());

            ingredients = new Ingredient[ingredientNum + 1];
            for(int j = 1 ; j <= ingredientNum; j++){
                st = new StringTokenizer(br.readLine());

                ingredients[j] = new Ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            solve(i, limitCalory);
        }
    }

    private static void solve(int testIdx, int limitCalory){
        int[][] dp = new int[ingredients.length][limitCalory + 1];

        for(int i = 1; i < ingredients.length; i++){
            for(int j = 1; j <= limitCalory; j++){
                if(ingredients[i].calory > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - ingredients[i].calory] + ingredients[i].value);
            }
        }

        System.out.println(String.format("#%d %d", testIdx + 1, dp[ingredients.length - 1][limitCalory]));
    }

    private static class Ingredient{
        int value;
        int calory;

        public Ingredient(int value, int calory){
            this.value = value;
            this.calory = calory;
        }
    }
    
}