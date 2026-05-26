package programmers.level3.금과은운반하기;

import java.util.*;
public class Code {
    public static void main(String[] args) {
        
    }

    static class Solution {
        int a, b, city;
        int[] g, s, t, w;
        
        static class Truck{
            int idx;
            long time;
            
            public Truck(int idx, long time){
                this.idx = idx;
                this.time = time;
            }
        }
        
        public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
            this.a = a; this.b = b; this.g = g; this.s = s; this.w = w; this.t = t;
            this.city = g.length;
            
            return solve();
        }
        
        long solve(){
            long start = 0;
            long end = 400000000000000l;
            long answer = end;
            
            while(start <= end){
                long mid = start + (end - start) / 2;
                
                if(isPossible(mid)){
                    answer = mid;
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }
            
            return answer;
        }
        
        boolean isPossible(long totalTime){
            long totalGold = 0;
            long totalSilver = 0;
            long totalMineral = 0;
            
            for(int i = 0; i < city; i++){
                long gold = g[i];
                long silver = s[i];
                int time = t[i];
                int weight = w[i];
                
                long moveCount = (totalTime) / (time * 2);
                
                if(totalTime % (time * 2) >= time) moveCount++;
                
                long maxCarry = weight * moveCount;
                
                totalGold += Math.min(gold, maxCarry);
                totalSilver += Math.min(silver, maxCarry);
                totalMineral += Math.min(gold + silver, maxCarry);
            }
            
            return totalGold >= a && totalSilver >= b && totalMineral >= (a + b);
        }
    }
}
