package programmers.level3.N더하기1카드게임;

import java.util.*;

public class Code {
    public static void main(String[] args) {
        
    }

    static class Solution {
        public int solution(int coin, int[] cards) {
            int n = cards.length;   
            boolean[] myHands = new boolean[n + 1];
            Set<Integer> candidates = new HashSet<>();        
            
            int pair = 0;
            for(int i = 0; i < n / 3; i++){
                int card = cards[i];
                if(myHands[n + 1- card]) pair++;
                myHands[card] = true;
            }
            
            int idx = n / 3;
            int round = 1;
            
            while(idx < n){
                candidates.add(cards[idx++]);
                candidates.add(cards[idx++]);
                
                
                boolean passed = false;
                
                if(pair > 0){
                    pair -= 1;
                    passed = true;
                }
                
                if(!passed && coin >= 1){
                    for(int candidate: candidates){
                        if(myHands[n + 1 - candidate]){
                            candidates.remove(candidate);
                            coin -= 1;
                            passed = true;
                            break;
                        }
                    }
                }
                
                if(!passed && coin >= 2){
                    for(int candidate: candidates){
                        if(candidates.contains(n + 1 - candidate)){
                            candidates.remove(candidate);
                            candidates.remove(n + 1 - candidate);
                            coin -= 2;
                            passed = true;
                            break;
                        }
                    }
                }
                
                if(!passed) break;
                round++;
            }
            
            return round;
        }
    }
}
