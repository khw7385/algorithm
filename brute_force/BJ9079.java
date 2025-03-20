package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BJ9079 {
    private static int testCase;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++){
            String matrix = "";

            for(int j = 0; j < 3; j++){
                matrix += br.readLine().replace(" ","");
            }

            int state = matrixToInt(matrix);

            System.out.println(bfs(state));
        }
    }

    private static void readTestCase()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        testCase = Integer.parseInt(br.readLine());
    }

    private static void solve()throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < testCase; i++){
            String matrix = "";

            for(int j = 0; j < 3; j++){
                matrix += br.readLine().replace(" ","");
            }

            int state = matrixToInt(matrix);

            System.out.println(bfs(state));
        }
    }

    private static int matrixToInt(String matrix){
        int result = 0;
        for(int i = 0; i < matrix.length(); i++){
            if(matrix.charAt(i) == 'H'){
                result |= 1 << (matrix.length() - 1 - i);
            }
        }
        return result;
    }

    private static int bfs(int startState){
        Queue<StateCount> q = new ArrayDeque<>();
        boolean[] visited = new boolean[1 << 9];


        q.add(new StateCount(startState, 0));

        while(!q.isEmpty()){
            StateCount curStateCount = q.poll();

            if(curStateCount.getState() == 0 || curStateCount.getState() == (1 << 9) - 1){
                return curStateCount.getCount();
            }
            int state;
            
            // 행 바꾸기
            for(int i = 0; i < 3; i++){
                state = curStateCount.getState();
                for(int j = 0; j < 3; j++){
                    state = state ^ (1 << (8 - (j + i * 3)));
                }

                if(!visited[state]){
                    visited[state] = true;
                    q.add(new StateCount(state, curStateCount.getCount() + 1));
                }
            }

            // 열 바꾸기
            for(int i = 0; i < 3; i++){
                state = curStateCount.getState();
                for(int j = 0; j < 3; j++){
                    state = state ^ (1 << (8 - (i + j * 3)));
                }
                if(!visited[state]){
                    visited[state] = true;
                    q.add(new StateCount(state, curStateCount.getCount() + 1));
                }
            }

            // 대각선 바꾸기
            state = curStateCount.getState();
            for(int i = 0; i < 3; i++){
                state = state ^ (1 << (8 - (i * 4)));
            }

            if(!visited[state]){
                visited[state] = true;
                q.add(new StateCount(state, curStateCount.getCount() + 1));
            }

            state = curStateCount.getState();
            for(int i = 0; i < 3; i++){
                state = state ^ (1 << (8 - (2 + i * 2)));
            }

            if(!visited[state]){
                visited[state] = true;
                q.add(new StateCount(state, curStateCount.getCount() + 1));
            }
        }

        return -1;
    }

    private static class StateCount{
        int state;
        int count;

        public StateCount(int state, int count){
            this.state = state;
            this.count = count;
        }

        public int getState(){
            return this.state;
        }

        public int getCount(){
            return this.count;
        }
    }
}
