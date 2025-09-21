package baekjoon_java.Gold.Level4.최소스패닝트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Main {
    private static int vertexCount, edgeCount;
    
    private static List<List<Edge>> vertexEdgeList = new ArrayList<>();
    private static List<Edge> edgeList = new ArrayList<>();

    public static void main(String[] args) {
        readInput();

        int answer = solveUsingPrim();
        System.out.println(answer);
    }

    private static void readInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String[] vertexAndEdgeCount = br.readLine().split(" ");

            vertexCount = Integer.parseInt(vertexAndEdgeCount[0]);
            edgeCount = Integer.parseInt(vertexAndEdgeCount[1]);

            for(int i = 0; i < vertexCount + 1; i++){
                vertexEdgeList.add(new ArrayList<>());
            }

            for(int i = 0; i < edgeCount; i++){
                String[] edgeInfo = br.readLine().split(" ");

                int fromVertex = Integer.parseInt(edgeInfo[0]);
                int toVertex = Integer.parseInt(edgeInfo[1]);
                int weight = Integer.parseInt(edgeInfo[2]);

                edgeList.add(new Edge(fromVertex, toVertex, weight));
                vertexEdgeList.get(fromVertex).add(new Edge(toVertex, weight));
                vertexEdgeList.get(toVertex).add(new Edge(fromVertex, weight));
            }

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solveUsingKruskal(){
        // 정렬 - 가중치 오름차순
        edgeList.sort((edge1, edge2) -> {
            return edge1.weight - edge2.weight;
        });

        DisjointSet disjointSet = new DisjointSet(vertexCount);

        int minWeightSum = 0;
        int edgeCount = 0;

        for(Edge edge: edgeList){
            if(edgeCount == vertexCount - 1) break;

            int fromVertexSet = disjointSet.find(edge.fromVertex);
            int toVertexSet = disjointSet.find(edge.toVertex);

            if(fromVertexSet == toVertexSet) continue;
            
            disjointSet.union(fromVertexSet, toVertexSet);
            minWeightSum += edge.weight;
            edgeCount++;
        }  
        
        return minWeightSum;
    }

    private static int solveUsingPrim(){
        PriorityQueue<Edge> pq = new PriorityQueue<>((edge1, edge2) -> edge1.weight - edge2.weight);
        boolean[] visited = new boolean[vertexCount + 1];

        int startVertex = 1;
        for(Edge edge: vertexEdgeList.get(startVertex)){
            pq.add(edge);
        }
        visited[startVertex] = true;

        int result = 0;
        int edgeCount = 0;
        while(!pq.isEmpty()){
            if(edgeCount == vertexCount - 1) break;
            
            Edge edge = pq.poll();
            if(visited[edge.toVertex]) continue;

            for(Edge e: vertexEdgeList.get(edge.toVertex)){   
                if(visited[e.toVertex]) continue; 
                pq.add(e);
            }

            result += edge.weight;
            edgeCount++;
            visited[edge.toVertex] = true;
        }

        return result;
    }

    static class Edge{
        int fromVertex;
        int toVertex;
        int weight;

        public Edge(int toVertex, int weight){
            this.toVertex = toVertex;
            this.weight = weight;
        }

        public Edge(int fromVertex, int toVertex, int weight){
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }
    }

    static class DisjointSet{
        private int[] arr;

        public DisjointSet(int n){
            arr = new int[n + 1];
            IntStream.range(0, n + 1)
                .forEach(idx -> arr[idx] = -1);
        }

        public int find(int node){
            if(arr[node] < 0) return node;
            return arr[node] = find(arr[node]);
        }

        public void union(int node1, int node2){
            int root1 = find(node1);
            int root2 = find(node2);

            if(root1 == root2) return;

            if(arr[root1] > arr[root2]){
                int tempNode = node1;
                node1 = node2;
                node2 = tempNode;
            }else if (arr[root1] == arr[root2]) {
                arr[root1]--;
            }

            arr[root2] = root1;
        }
    }
}
