package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BJ2252{
    private static int n;
    private static int[][] comps;
    public static void main(String[] args) {
        readInput();
        Solution solution = new Solution();
        int[] answer= solution.solve(n, comps);

        System.out.println(
            Arrays.stream(answer)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "))
        );
    }

    private static void readInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String[] inputNM = br.readLine()
                .split(" ");
            n = Integer.parseInt(inputNM[0]);
            int m = Integer.parseInt(inputNM[1]);
            comps = new int[m][2];

            StringTokenizer st;
            for(int i = 0; i < m ; i++){
                st = new StringTokenizer(br.readLine());
                comps[i][0] = Integer.parseInt(st.nextToken());
                comps[i][1] = Integer.parseInt(st.nextToken());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static class Solution{
        public int[] solve(int n, int[][] comps){
            int[] indegrees = new int[n + 1];

            // graph 준비
            List<List<Integer>> graph = new ArrayList<>(n + 1);
            
            for(int i = 0; i <= n; i++){
                graph.add(new ArrayList<>());
            }

            // 그래프 초기화 및 진입 차수 파악
            for(int i = 0 ; i < comps.length; i++){
                graph.get(comps[i][0]).add(comps[i][1]);
                indegrees[comps[i][1]]++;
            }

            List<Integer> answer = new ArrayList<>();
            Queue<Integer> queue = new ArrayDeque<>();            
            
            for(int i = 1 ; i <= n; i++){
                if(indegrees[i] == 0) queue.add(i);
            }

            while(!queue.isEmpty()){
                int now = queue.poll();
                answer.add(now);

                for(int end : graph.get(now)){
                    indegrees[end]--;
                    if(indegrees[end] == 0) queue.add(end);
                }
            }

            return answer.stream()
                    .mapToInt(Integer::intValue)
                    .toArray();
        }
    }
}