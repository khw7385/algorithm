package baekjoon_java.Gold.Level3.모양만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] arr;
    private static int[][] groups;
    private static Map<Integer, Integer> groupMap = new HashMap<>();

    private static int[] dx = { 1, 0 , -1, 0};
    private static int[] dy = { 0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        groups = new int[n][m];
        
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = solve();
        System.out.println(answer);
    }

    private static int solve(){
        classfiy();

        int result = groupMap.get(1);

        for(int x = 0; x < n; x++){
            for(int y = 0; y < m; y++){
                if(groups[x][y] != 0) continue;

                int size = 1;
                Set<Integer> isCheckeds = new HashSet<>();  
                for(int k = 0; k < 4; k++){
                    int nextX = x + dx[k];
                    int nextY = y + dy[k];

                    if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                    
                    int group = groups[nextX][nextY];
                    
                    if(group == 0) continue;
                    if(isCheckeds.contains(group)) continue;

                    size += groupMap.get(group);
                    isCheckeds.add(group);
                }

                result = Math.max(result, size);
            }
        }

        return result;
    }

    private static void classfiy(){
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();

        int group = 1;
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < m; j++){

                if(visited[i][j]) continue;
                if(arr[i][j] == 0) continue;

                int groupSize = 0;
                int[] point = {i, j};

                groups[i][j] = group;
                visited[i][j] = true;
                queue.add(point);
                groupSize++;
                
                while(!queue.isEmpty()){
                    point = queue.poll();
                    int x = point[0];
                    int y = point[1];

                    for(int k = 0; k < 4; k++){
                        int nextX = x + dx[k];
                        int nextY = y + dy[k];

                        if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                        if(visited[nextX][nextY]) continue;
                        if(arr[nextX][nextY] == 0) continue;

                        int[] nextPoint = {nextX, nextY};

                        queue.add(nextPoint);
                        groups[nextX][nextY] = group;
                        visited[nextX][nextY] = true;
                        groupSize++;
                    }
                }
                groupMap.put(group, groupSize);
                group++;    
            }
        }
    }
}

// 직접 해본다.
// N * M * N * M: 

// 사실 하나만 바꾸는 것이기 때문에, 크기를 구하는 과정을 반복할 필요가 없다.
// 이미 특정 칸의 크기를 알고 있다면
// 특정 위치를 바꾼다고 했을 때 최대 크기를 상하좌우 칸의 크기와 합쳐 최대값이랑 비교하면 된다.
// 그러면, 과정이 크기를 구하는 과정/연산을 하면 된다.

// 탐색(BFS/DFS): 둘 중 무엇을 해도 상관이 없다. 
// -> 근데, 완전 탐색을 했을 때 각 칸의 크기를 표시하는 것까지 포함하면 2배를 해야 한다.
// 크기를 구하는 탐색(선) -> 크기를 표시하는 탐색(후): 2배 일
// 크기를 구하는 탐색은 무조건 해야 된다.
// 근데, 크기를 표시하기 위해 탐색을 해야 할까? 아니다. 크기를 표시할 필요없이 같은 집합임을 다루는 표시를 하면 된다.
// 예를 들면, 집합 A 라고 표시한다고 가정하고 A: 크기 4 라고 표시를 하면 된다.
// 그러면 탐색 연산은 N 의 시간 복잡도를 갖는다.
// 더 줄일 필요가 없는데
