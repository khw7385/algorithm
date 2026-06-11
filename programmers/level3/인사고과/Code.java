package programmers.level3.인사고과;

import java.util.*;

public class Code {
    public static void main(String[] args) {
        
    }

    class Solution {
        public int solution(int[][] scores) {
            int[][] copyScores = new int[scores.length - 1][2];
            
            for(int i = 0; i < copyScores.length; i++){
                for(int j = 0; j < 2; j++){
                    copyScores[i][j] = scores[i + 1][j];
                }
            }
            
            Arrays.sort(copyScores, (o1, o2) -> {
                if(o1[0] == o2[0]) return Integer.compare(o1[1], o2[1]);
                return Integer.compare(o2[0], o1[0]);
            });
            
            int rank = 1;
            int maxEvlNum = 0;
            
            int[] wanho = { scores[0][0], scores[0][1] };
            int wanhoSum = wanho[0] + wanho[1];
            
            for(int i = 0; i < copyScores.length; i++){
                if(wanho[0] < copyScores[i][0] && wanho[1] < copyScores[i][1]) return -1;
                
                if(copyScores[i][1] < maxEvlNum) continue;
                maxEvlNum = Math.max(copyScores[i][1], maxEvlNum);
                
                if(wanhoSum < copyScores[i][0] + copyScores[i][1]) rank++;
            }
            
            return rank;
        }
    }
}
