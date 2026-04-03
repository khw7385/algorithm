package baekjoon_java.Platinum.LEVEL5.거의최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int vNum, eNum, start, end;
    private static boolean[][] isRemoved;
    private static Map<Integer, List<int[]>> vEdges = new HashMap<>();
    private static Map<Integer, List<int[]>> rvEdges = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            vNum = Integer.parseInt(st.nextToken());
            eNum = Integer.parseInt(st.nextToken());

            if(vNum == 0 && eNum == 0) break;

            isRemoved = new boolean[vNum][vNum];

            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            for(int i = 0; i < vNum; i++){
                List<int[]> edges1 = new ArrayList<>();
                vEdges.put(i, edges1);
                List<int[]> edges2 = new ArrayList<>();
                rvEdges.put(i, edges2);
            }

            for(int i = 0; i < eNum; i++){
                st = new StringTokenizer(br.readLine());
                
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                List<int[]> edges1 = vEdges.get(v1);
                int[] pair1 = { v2, weight };
                edges1.add(pair1);

                List<int[]> edges2 = rvEdges.get(v2);
                int[] pair2 = { v1, weight };
                edges2.add(pair2);
            }

            long result = solve();

            if(result == Long.MAX_VALUE){
                System.out.println(-1);
            }else{
                System.out.println(result);
            }
        }
    }

    private static long solve(){
        long[] dist = dijkstra();

        Queue<Node> queue = new ArrayDeque<>();
        boolean[] isVisited = new boolean[vNum];

        queue.add(new Node(end, dist[end]));
        isVisited[end] = true;

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            for(int[] next: rvEdges.get(cur.v)){
                int nextV = next[0];
                int nextW = next[1];


                if(dist[nextV] + nextW == cur.dist){
                    isRemoved[nextV][cur.v] = true;

                    if(!isVisited[nextV]){
                        isVisited[nextV] = true;
                        queue.add(new Node(nextV, dist[nextV]));
                    }
                }
            }
        }

        return dijkstra2();
    }

    private static long[] dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue();
        long[] dist = new long[vNum];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[start] = 0;
        pq.add(new Node(start, 0));

        // 다익스트라 알고리즘
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            
            int curV = curNode.v;
            long curDist = curNode.dist;

            if(curDist > dist[curV]) continue;

            for(int[] next: vEdges.get(curV)){
                int nextV = next[0];
                int weight = next[1];

                if(curDist + weight < dist[nextV]){
                    dist[nextV] = dist[curV] + weight;
                    pq.add(new Node(nextV, dist[nextV]));
                }
            }
        }
        return dist;
    }

    private static long dijkstra2(){
        PriorityQueue<Node> pq = new PriorityQueue();
        long[] dist = new long[vNum];
        Arrays.fill(dist, Long.MAX_VALUE);

        dist[start] = 0;
        pq.add(new Node(start, 0));

        // 다익스트라 알고리즘
        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            
            int curV = curNode.v;
            long curDist = curNode.dist;

            if(curDist > dist[curV]) continue;

            for(int[] next: vEdges.get(curV)){
                int nextV = next[0];
                int weight = next[1];
                
                if(isRemoved[curV][nextV]) continue;

                if(curDist + weight < dist[nextV]){
                    dist[nextV] = dist[curV] + weight;
                    pq.add(new Node(nextV, dist[nextV]));
                }
            }
        }

        return dist[end];
    }


    private static class Node implements Comparable<Node>{
        int v;
        long dist;

        public Node(int v, long dist){
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other){
            return Long.compare(dist, other.dist);
        }
    }
}
