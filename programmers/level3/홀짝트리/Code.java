package programmers.level3.홀짝트리;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nodes = { 11, 9, 3, 2, 4, 6 };
        int[][] edges = { { 9, 11 }, { 2, 3 }, { 6, 3 }, { 3, 4} };
        
        int[] answer = solution.solution(nodes, edges);

        System.out.println(Arrays.toString(answer));
    }

    private static class Solution{
        private static final int YELLOW = 0;
        private static final int RED = 1;

        private static final int MAX_NODE_NUM = 1_000_000;

        private Set<Integer> visited = new HashSet<>();
        private Map<Integer, List<Integer>> graph = new HashMap<>();
        private int yellow, red;

        public int[] solution(int[] nodes, int[][] edges) {
            init(nodes, edges);

            int[] result = { 0, 0 };

            for(int i = 0; i < nodes.length; i++){
                int rootNode = nodes[i];
                
                yellow = 0;
                red = 0;

                searchTree(rootNode);

                if(yellow == 1) result[YELLOW] += 1;
                
                if(red == 1) result[RED] += 1;
            }

            return result;
        }        

        private void init(int[] nodes, int[][] edges){
            for(int i = 0 ; i < nodes.length; i++){
                graph.put(nodes[i], new ArrayList<>());
            }

            for(int i = 0; i < edges.length; i++){
                int[] edge = edges[i];

                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
        }

        private void searchTree(int node){
            if(visited.contains(node)){
                return;
            }

            int tree = (node % 2 == graph.get(node).size() % 2 )? YELLOW : RED;

            if(tree == RED) red++;
            else yellow++;

            visited.add(node);

            for(int child: graph.get(node)){
                if(visited.contains(child)) continue;

                searchTree(child);                
            }
        }
    }
}
