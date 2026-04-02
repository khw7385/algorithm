package baekjoon_java.Platinum.LEVEL5.도로포장;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m, k;
    private static Map<Integer, List<int[]>> vEdges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int i = 1 ; i <= n; i++){
            List<int[]> edges = new ArrayList<>();
            vEdges.put(i, edges);
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            List<int[]> edges1 = vEdges.get(v1);
            int[] edge1 = { v2, weight };
            edges1.add(edge1);

            List<int[]> edges2 = vEdges.get(v2);
            int[] edge2 = { v1, weight };
            edges2.add(edge2);
        }

        long result = solve();
        System.out.println(result);
    }

    private static long solve(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[][] dist = new long[n + 1][k + 1];

        for(int i = 2 ; i <= n; i++){
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        
        pq.add(new Node(1, 0, 0));
        dist[1][0] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            int curV = cur.vertex;
            long curDist = cur.dist;
            int curPave = cur.pave;

            if(curDist > dist[curV][curPave]) continue;

            for(int[] next: vEdges.get(curV)){
                int nextV = next[0];
                int weight = next[1];

                if(curDist + weight < dist[nextV][curPave]){
                    dist[nextV][curPave] = curDist + weight;
                    pq.add(new Node(nextV, curDist + weight, curPave));
                }

                if(curPave < k && curDist < dist[nextV][curPave + 1]){
                    dist[nextV][curPave + 1] = curDist;
                    pq.add(new Node(nextV, curDist, curPave + 1));
                }
            }
        }

        long minResult = Long.MAX_VALUE;
        for (int i = 0; i <= k; i++) {
            minResult = Math.min(minResult, dist[n][i]);
        }
    
        return minResult;
    }

    private static class Node implements Comparable<Node>{
        int vertex;
        long dist;
        int pave;

        public Node(int vertex, long dist, int pave){
            this.vertex = vertex;
            this.dist = dist;
            this.pave = pave;
        }

        @Override
        public int compareTo(Node other){
            return Long.compare(dist, other.dist);
        }
    }
}
