package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ11725{
    private static int nodeCount;
    private static Map<Integer, List<Integer>> edges = new HashMap<>();
    private static int[] result;

    public static void main(String[] args) throws IOException{
       readInput();
       solve();

       for(int i = 1; i < result.length; i++){
            System.out.println(result[i]);
       }    
    }

    private static void readInput()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeCount = Integer.parseInt(br.readLine());
        result = new int[nodeCount];

        for(int i = 0; i < nodeCount - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int node1 = Integer.parseInt(st.nextToken()); 
            int node2 = Integer.parseInt(st.nextToken());

            if(edges.containsKey(node1)){
                edges.get(node1).add(node2);
            }else{
                edges.put(node1, new ArrayList<>(Arrays.asList(node2)));
            }

            if(edges.containsKey(node2)){
                edges.get(node2).add(node1);
            }else{
                edges.put(node2, new ArrayList<>(Arrays.asList(node1)));
            }
        }
    }

    private static void solve(){
        Queue<Integer> q = new ArrayDeque<>();

        q.add(1);

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int next: edges.get(cur)){
                if(result[next - 1] == 0){
                    result[next -1] = cur;
                    q.add(next);
                }
            }
        }
    }
}