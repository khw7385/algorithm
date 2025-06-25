package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ1260 {
    private static int n, m, v;
    private static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args)throws IOException{  
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>(n + 1);
        for(int i = 0; i <= n; i++){
            graph.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 1; i <= n; i++){
            Collections.sort(graph.get(i));
        }
    }

    private static void solve(){
        dfs();
        System.out.println();
        bfs();
    }

    private static void bfs(){
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        List<Integer> result = new ArrayList<>();
        
        queue.add(v);
        visited[v] = true;
        result.add(v);

        while(!queue.isEmpty()){
            Integer cur = queue.poll();
            
            for(Integer next: graph.get(cur)){
                if(visited[next]) continue;
                queue.add(next);
                visited[next] = true;
                result.add(next);
            }
        }

        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
    }

    private static void dfs(){
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[n + 1];
        List<Integer> result = new ArrayList<>();

        stack.add(v);

        while (!stack.isEmpty()) {
            Integer cur = stack.pop();  
            
            if(visited[cur]) continue;
            visited[cur] = true;
            result.add(cur);

            List<Integer> nexts = graph.get(cur);
            for(int i = 0; i < nexts.size(); i++){
                Integer next = nexts.get(nexts.size() - 1 - i);
                if(visited[next]) continue;
                stack.add(next);
            }
        }

        for(int i = 0; i < result.size(); i++){
            System.out.print(result.get(i) + " ");
        }
    }
}


