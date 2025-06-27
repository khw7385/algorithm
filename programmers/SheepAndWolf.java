package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SheepAndWolf {
    private int[] nodes = new int[17];
    private List<Integer>[] graph = new ArrayList[17];
    private int result = 0;
    private boolean[][][] visited = new boolean[17][17][17];
    
    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        solve();
        
        return result;
    }
    
    private void init(int[] info, int[][] edges){
        for(int i = 0; i < info.length; i++){
            nodes[i] = info[i];
        }
        
        for(int i = 0; i < graph.length;i++){
            graph[i] = new ArrayList<>();    
        }
        
        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
    }
    
    private void solve(){
        nodes[0] = -1;
        dfs(0, 1, 0);
    }    
    
    private void dfs(int idx, int sheep, int wolf){
        if(idx == 0){
            result = Math.max(result, sheep);
        }
        
        for(int v : graph[idx]){
            if(nodes[v] == 0){
                if(visited[v][sheep + 1][wolf]) continue;
                nodes[v] = -1;
                visited[v][sheep + 1][wolf] = true;
                dfs(v, sheep + 1, wolf);
                nodes[v] = 0;
                visited[v][sheep + 1][wolf] = false;
            }else if(nodes[v] == 1){
                if(sheep <= wolf + 1) continue;
                if(visited[v][sheep][wolf + 1]) continue;
                nodes[v] = -1;
                visited[v][sheep][wolf + 1] = true;
                dfs(v, sheep, wolf + 1);
                nodes[v] = 1;
                visited[v][sheep][wolf + 1] = false;
            }else{
                if(visited[v][sheep][wolf]) continue;
                visited[v][sheep][wolf] = true;
                dfs(v, sheep, wolf);
                visited[v][sheep][wolf] = false;
            }
        }
    }

    LinkedList
}