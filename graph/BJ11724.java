package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11724 {
    private static int n, m;
    private static List<List<Integer>> graph;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(n + 1);
        visited = new boolean[n + 1];

        for(int i = 0; i <= n; i++){
            graph.add((new ArrayList<>()));
        }

        for(int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            graph.get(v).add(u);
        }
    }

    private static void solve(){
        int result = 0;
        Queue<Integer> queue = new ArrayDeque<>();

        for(int i = 1; i <= n; i++){
            if(visited[i]) continue;

            queue.add(i);
            visited[i] = true;

            while(!queue.isEmpty()){
                Integer cur = queue.poll();

                for(Integer next: graph.get(cur)){
                    if(visited[next]) continue;
                    queue.add(next);
                    visited[next] = true;
                }
            }
            result++;
        }

        System.out.println(result);
    }
}
