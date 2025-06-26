package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1062 {
    private static int n, k;
    private static String[] words;
    private static boolean[] isUsed = new boolean[26];
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }
    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];
        for(int i = 0; i < n; i++){
            words[i] = br.readLine();
        }
    }

    private static void solve(){
        if(k < 5){
            System.out.println(0);
            return;
        }
        isUsed['a' - 'a'] = true;
        isUsed['c' - 'a'] = true;
        isUsed['i' - 'a'] = true;
        isUsed['n' - 'a'] = true;
        isUsed['t' - 'a'] = true;

        dfs(0, 0);

        System.out.println(result);
    }

    private static void dfs(int idx, int cnt){
        if(cnt == k - 5){
            result = Math.max(result, calcMaxWord());
            return;
        }

        for(int i = idx; i < 26; i++){
            if(isUsed[i]) continue;
            isUsed[i] = true;
            dfs(i + 1, cnt + 1);
            isUsed[i] = false;
        }
    }

    private static int calcMaxWord(){
        int cnt = 0;
        for(int i = 0 ; i < n; i++){
            boolean canTeach = true;

            for(int j = 4; j <= words[i].length() - 4; j++){
                if(!isUsed[words[i].charAt(j) - 'a']){
                    canTeach = false;
                    break;
                }
            }
            if(canTeach) cnt++;
        }

        return cnt;
    }
}

