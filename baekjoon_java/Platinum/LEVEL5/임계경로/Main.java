package baekjoon_java.Platinum.LEVEL5.임계경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int startNode, endNode;
    static List<Node>[] graph, rGraph;
    static int[] inDegree;

    static class Node {
        int target, weight;
        Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        rGraph = new ArrayList[n + 1];
        inDegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            rGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            rGraph[v].add(new Node(u, w));
            inDegree[v]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        startNode = Integer.parseInt(st.nextToken());
        endNode = Integer.parseInt(st.nextToken());

        solve();
    }

    private static void solve(){
        Queue<Integer> queue = new ArrayDeque<>();
        int[] costs = new int[n + 1];

        costs[startNode] = 0;
        queue.add(startNode);

        while(!queue.isEmpty()){
            int cur = queue.poll();

            for(Node next: graph[cur]){
                inDegree[next.target]--;
                
                costs[next.target] = Math.max(costs[next.target], costs[cur] + next.weight);

                if(inDegree[next.target] == 0){
                    queue.add(next.target);
                }
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n + 1];
        int goldenRoad = 0;

        q.add(new Node(endNode, costs[endNode]));

        while(!q.isEmpty()){
            Node curNode = q.poll();

            for(Node nextNode: rGraph[curNode.target]){
                if(nextNode.weight + costs[nextNode.target] == costs[curNode.target]){
                    goldenRoad++;

                    if(isVisited[nextNode.target]) continue;
                    isVisited[nextNode.target] = true;
                    q.add(new Node(nextNode.target, nextNode.weight));
                }
            }
        }

        System.out.println(costs[endNode]);
        System.out.println(goldenRoad);
    }
}
