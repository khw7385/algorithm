package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1697 {
    private static int n, k;
    private static int[] time = new int[100001];

    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }
    
    private static void readInput()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Arrays.fill(time, -1);
    }

    private static void solve(){
        int cur, next;

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(n);
        time[n] = 0;

        while(!queue.isEmpty()){
            cur = queue.poll();

            next = cur + 1;
            if(next >= 0 && next <= 100000 && time[next] == -1){
                time[next] = time[cur] + 1;
                if(next == k) break;
                queue.add(next);
            }

            next = cur - 1;
            if(next >= 0 && next <= 100000 && time[next] == -1){
                time[next] = time[cur] + 1;
                if(next == k) break;
                queue.add(next);
            }

            next = 2 * cur;
            if(next >= 0 && next <= 100000 && time[next] == -1){
                time[next] = time[cur] + 1;
                if(next == k) break;
                queue.add(next);
            }
        }

        System.out.println(time[k]);
    }
}
