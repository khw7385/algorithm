package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ9251 {
    private static String str1, str2;
    private static int[][] d;
    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            str1 = br.readLine();
            str2 = br.readLine();

            d = new int[str1.length() + 1][str2.length() + 1];
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solve(){
        // 초기화
        for(int i = 1; i <= str1.length(); i++){
            for(int j = 1; j <= str2.length(); j++){
                if (str1.charAt(i - 1) == str2.charAt(j - 1)){
                    d[i][j] = Math.max(d[i - 1][j - 1] + 1, Math.max(d[i - 1][j], d[i][j - 1]));
                }else{
                    d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
                }
            }
        }

        return d[str1.length()][str2.length()];
    }
}
