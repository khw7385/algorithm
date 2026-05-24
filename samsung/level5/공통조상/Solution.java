package samsung.level5.공통조상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    static int vertex, edge, tv1, tv2;
    static Map<Integer, List<Integer>> tree;
    static Map<Integer, List<Integer>> rTree;

    static int commonV, subtreeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int test = Integer.parseInt(br.readLine());

        for(int i = 1; i <= test; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            vertex = Integer.parseInt(st.nextToken());
            edge = Integer.parseInt(st.nextToken());
            tv1 = Integer.parseInt(st.nextToken());
            tv2 = Integer.parseInt(st.nextToken());

            tree = new HashMap<>();
            rTree = new HashMap<>();

            for(int v = 1; v <= vertex; v++){
                tree.put(v, new ArrayList<>());
                rTree.put(v, new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());

            for(int e = 0; e < edge; e++){
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                List<Integer> vEdges = tree.get(v1);
                List<Integer> rvEdges = rTree.get(v2);

                vEdges.add(v2);
                rvEdges.add(v1);
            }

            solve();

            System.out.println(String.format("#%d %d %d", i, commonV, subtreeSize));
        }
    }

    static void solve(){
        commonV = findCommonVertex(1);
        subtreeSize = calcSubTreeSize(commonV);
    }

    static int findCommonVertex(int curV){
        if(curV == tv1 || curV == tv2){
            return -1;
        }
        
        int status = 0;
        
        for(int nextV: tree.get(curV)){
            int nextStatus = findCommonVertex(nextV);
            if(nextStatus == -1) status--;
            else if(nextStatus != 0) return nextStatus;
        }

        if(status == -2) return curV;

        return status;
    }

    static int calcSubTreeSize(int curV){
        if(tree.get(curV).size() == 0){
            return 1;
        }

        int size = 1;
        for(int nextV: tree.get(curV)){
            size += calcSubTreeSize(nextV);
        }

        return size;
    }
}
