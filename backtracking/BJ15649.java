package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ15649 {
    private static int n, m;
    private static boolean[] visited;
    private static int[] arr;


    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    private static void solve(){
        visited = new boolean[n + 1];
        arr = new int[n + 1];

        dfs(0);
    }

    private static void dfs(int num){
        if(num == m){
            for(int i = 0; i < m; i++){
                System.out.print(arr[i]);
                if(i != m - 1) System.out.print(" ");
            }
            System.out.println();
        }
        
        for(int i = 1; i <= n; i++){
            if(visited[i]) continue;

            visited[i] = true;
            arr[num] = i;
            dfs(num + 1);

            visited[i] = false;
        }
    }
}
