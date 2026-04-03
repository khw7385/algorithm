package baekjoon_java.Platinum.LEVEL5.StronglyConnectecComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int vNum, eNum;
    private static int timer = 0;
    private static Map<Integer, List<Integer>> vEdges = new HashMap<>();
    
    private static int[] group;
    private static Stack<Integer> stack = new Stack<>();
    private static boolean[] isFinished;
    private static List<List<Integer>> sccList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        vNum = Integer.parseInt(st.nextToken());
        eNum = Integer.parseInt(st.nextToken());
        
        for(int i = 1; i <= vNum; i++){
            List<Integer> edges = new ArrayList<>();
            vEdges.put(i, edges);
        }

        group = new int[vNum + 1];
        isFinished = new boolean[vNum + 1];

        for(int i = 0; i < eNum; i++){
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            List<Integer> edges = vEdges.get(v1);
            edges.add(v2);
        }

        solve();
    }

    private static void solve(){
        for(int i = 1; i <= vNum; i++){
            if(group[i] == 0) dfs(i);
        }

        for(List<Integer> scc: sccList){
            Collections.sort(scc);
        }

        Collections.sort(sccList, (o1, o2) -> o1.get(0) - o2.get(0));

        StringBuilder sb = new StringBuilder();
        sb.append(sccList.size()).append("\n");
        for (List<Integer> scc : sccList) {
            for (int val : scc) {
                sb.append(val).append(" ");
            }
            sb.append("-1\n");
        }
        System.out.print(sb);
    }

    private static int dfs(int num){
        group[num] = ++timer;
        stack.push(num);

        int groupKing = group[num];
        for(int next: vEdges.get(num)){
            // 아직 방문하지 않았다면
            if(group[next] == 0){
                groupKing = Math.min(groupKing, dfs(next));
            } // 아직 방문 중이라면
            else if(!isFinished[next]){
                groupKing = Math.min(groupKing, group[next]);
            }
        }

        // 내가 그룹의 대장이라면(그룹의 대장 = 방문 순서이다.)
        if(group[num] == groupKing){
            List<Integer> scc = new ArrayList<>();
            while(true){
                int t = stack.pop();
                scc.add(t);
                isFinished[t] = true;
                if(t == num) break;
            }
            sccList.add(scc);
        }
        return groupKing;
    }
}
