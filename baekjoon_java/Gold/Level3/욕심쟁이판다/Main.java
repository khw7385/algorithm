package baekjoon_java.Gold.Level3.욕심쟁이판다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[][] forest;

    private static int[] dx = { 1, 0, -1, 0};
    private static int[] dy = { 0, 1, 0, -1};

    private static int[][] dpArr;
    public static void main(String[] args) {
        init();
        int answer = solve();

        System.out.println(answer);
    }

    private static void init(){
        readInput();
        dpArr = new int[n][n];
    }

    private static void readInput(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            n = Integer.parseInt(br.readLine());
            forest = new int[n][n];

            for(int i = 0 ; i < n ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                
                for(int j = 0 ; j < n; j++){
                    forest[i][j] = Integer.parseInt(st.nextToken());    
                }
            }

        }catch(IOException e){
            System.out.println("입력 처리 중 오류 발생");
        }
    
    }
    
    private static int solve(){
        // 완전 탐색(절차적인 방법)
        // 시작위치 정하기: O(N * N)
        // 완전 탐색: 정확히는 못 구함
        
        //  1. 모든 영역을 탐색한다면: O(N * N) => 근데, 이전보다 많은 영역을 탐색해야 되므로 시간 단축! 500 * 500 = 250000
        

        int result = 0;

        // 각 경우에 대해서 중복된 방문 경우가 존재 => 방문에 대한 상태 관리가 필요
        // dpArr로 한 번에 처리할 수 없음
        // 1. 모든 시작 지점에 대해서 중복된 연산을 하지 않기 위한 용도
        // 2. 별도의 공간 마련? 

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int visit = dfs(i, j);

                // System.out.println(String.format("(%d, %d) >> %d", i, j, visit));
                result = Math.max(result, visit);
            }
        }
        
        return result;
    }

    private static int dfs(int x, int y){
        // 종료조건 => 다음 차례로 곧 못가는게 종료 조건
        // 중복된 연산 => dp
        if(dpArr[x][y] != 0){
            return dpArr[x][y];
        }

        // 다음 차례
        int visit = 0; // 자기 자신 방문
        
        for(int i = 0 ; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX < 0 | nextX >= n || nextY < 0 || nextY >= n) continue;
            if(forest[nextX][nextY] <= forest[x][y]) continue;
            
            visit = Math.max(visit, dfs(nextX, nextY));
        }

        return dpArr[x][y] = visit + 1;
    }
}
