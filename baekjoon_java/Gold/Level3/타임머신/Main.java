package baekjoon_java.Gold.Level3.타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int vNum, eNum;
    private static List<Edge> edges = new ArrayList<>();

    private static final long INF = 9876543210L;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        vNum = Integer.parseInt(st.nextToken());
        eNum = Integer.parseInt(st.nextToken());

        for(int i = 0; i < eNum; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(v1, v2, weight));
        }

        solve();
    }

    private static void solve(){
        long[] dist = new long[vNum + 1];

        dist[1]= 0 ;
        for(int i = 2; i <= vNum; i++){
            dist[i] = INF;
        }

        for(int i = 1; i < vNum; i++){
            for(Edge edge: edges){
                if(dist[edge.start] != INF){
                    dist[edge.end] = Math.min(dist[edge.end], dist[edge.start] + edge.weight);
                }
            }
        }

        long[] tempDist = Arrays.copyOf(dist, vNum + 1);
        for(Edge edge: edges){
            if(dist[edge.start] != INF){
                tempDist[edge.end] = Math.min(tempDist[edge.end], tempDist[edge.start] + edge.weight);
            }
        }

        for(int i = 1; i <= vNum; i++){
            if(tempDist[i] != dist[i]){
                System.out.println(-1);
                return;
            }
        }

        for(int i = 2; i <= vNum; i++){
            if(dist[i] == INF){
                System.out.println(-1);
            }else{
                System.out.println(dist[i]);
            }
        }
    }

    private static class Edge{
        int start;
        int end;
        int weight;

        public Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}
