package baekjoon_java.Gold.Level2.집구하기;

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
    private static int vNum, eNum, sNum, mNum, sDist, mDist;
    private static int[] sVs, mVs;
    private static Map<Integer, List<int[]>> vEdges = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        vNum = Integer.parseInt(st.nextToken());
        eNum = Integer.parseInt(st.nextToken());

        for(int v = 1; v <= vNum; v++){
            vEdges.put(v, new ArrayList<>());
        }

        for(int i = 0; i < eNum; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            List<int[]> edges1 = vEdges.get(v1);
            int[] pair1 = { v2, weight };
            edges1.add(pair1);

            List<int[]> edges = vEdges.get(v2);
            int[] pair2 = { v1, weight };
            edges.add(pair2);
        }
        
        st = new StringTokenizer(br.readLine());

        sNum = Integer.parseInt(st.nextToken());
        sDist = Integer.parseInt(st.nextToken());

        sVs = new int[sNum];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < sNum; i++){
            sVs[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        mNum = Integer.parseInt(st.nextToken());
        mDist = Integer.parseInt(st.nextToken());

        mVs = new int[mNum];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < mNum; i++){
            mVs[i] = Integer.parseInt(st.nextToken());
        }

        long result = solve();

        if(result == Long.MAX_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(result);
        }
    }

    private static long solve(){
        // 스타벅스 최단거리 구하기
        List<int[]> edges = new ArrayList<>();
        for(int i = 0; i < sNum; i++){
            int[] pair = {sVs[i], 0};
            edges.add(pair);
        }

        vEdges.put(0, edges);

        long[] sDists = dijkstra();

        edges = new ArrayList<>();
        for(int i = 0; i < mNum; i++){
            int[] pair = {mVs[i], 0};
            edges.add(pair);
        }

        vEdges.put(0, edges);
        
        long[] mDists = dijkstra();

        long result = Long.MAX_VALUE;
        
        for(int i = 1; i < mDists.length; i++){
            if(isMacOrStar(i)) continue;

            if(mDists[i] > mDist) continue;
            if(sDists[i] > sDist) continue;

            result = Math.min(result, mDists[i] + sDists[i]);
        }

        return result;
    }

    private static long[] dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] dists = new long[vNum + 1];
        Arrays.fill(dists, Long.MAX_VALUE);

        pq.add(new Node(0, 0));
        dists[0] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            
            int nowV = now.next;
            
            for(int[] next: vEdges.get(nowV)){
                int nextV = next[0];
                int dist = next[1];

                if(dists[nextV] > dists[nowV] + dist){
                    dists[nextV] = dists[nowV] + dist;
                    pq.add(new Node(nextV, dists[nextV]));
                }
            }
        }

        return dists;
    }

    private static boolean isMacOrStar(int num){
        for(int i = 0 ; i < sVs.length; i++){
            if(num == sVs[i]) return true;
        }

        for(int i = 0 ; i < mVs.length; i++){
            if(num == mVs[i]) return true;
        }

        return false;
    }

    private static class Node implements Comparable<Node>{
        private int next;
        long dist;

        public Node(int next, long dist){
            this.next = next;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o){
            return Long.compare(dist, o.dist);
        }
    }
}
