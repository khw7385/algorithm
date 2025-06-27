package programmers;

import java.util.*;

public class ChartEdit {
    public static void main(String[] args) {   
        Solution solution = new Solution();
        String[] cmd = {"D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"};
        String answer = solution.solution(8, 2, cmd);

        System.out.println(answer);
    }

    static class Solution {
        private int chartSize, cursor;
        private Stack<Integer> trashCan;
        private String result;

        public String solution(int n, int k, String[] cmd){
            init(n, k);
            solve(cmd);
            return result;
        }

        private void init(int n, int k){
            chartSize = n;
            cursor = k;
            trashCan = new Stack<Integer>();
        }
        
        private void solve(String[] cmd){
            for(int i = 0; i < cmd.length; i++){
                String[] cmdArr = cmd[i].split(" ");
                char op = cmdArr[0].charAt(0);

                if(op == 'U'){
                    int num = Integer.parseInt(cmdArr[1]);
                    up(num);
                }
                else if(op == 'D'){
                    int num = Integer.parseInt(cmdArr[1]);
                    down(num);
                }
                else if(op == 'C') delete();
                else reset();
            }

            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < chartSize; i++){
                sb.append("O");
            }

            while(!trashCan.isEmpty()){
                int trash = trashCan.pop();
                sb.insert(trash, "X");
            }

            result = sb.toString();
        }

        private void up(int num){
            for(int i = 0 ; i < num; i++) cursor--;
        }

        private void down(int num){
            for(int i = 0 ; i < num; i++) cursor++;
        }

        private void delete(){
            trashCan.push(cursor);
            chartSize--;
            if(cursor == chartSize) cursor--;
        }

        private void reset(){
            int trash = trashCan.pop();
            chartSize++;
            if(trash <= cursor) cursor++;
        }
    }
}
