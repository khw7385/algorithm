package programmers.level2.퍼즐게임챌린지;

public class Code {
    public static void main(String[] args) {
        
    }
    
    class Solution {
        int[] diffs, times;
        long limit;
        
        public int solution(int[] diffs, int[] times, long limit) {
            this.diffs = diffs; this.times = times; this.limit = limit;
            
            int answer = solve();
            return answer;
        }
        
        private int solve(){
            int left = 1;
            int right = 1;
            
            for(int i = 0; i< diffs.length; i++){
                right = Math.max(right, diffs[i]);
            }
            
            int answer = right;
            
            while(left <= right){
                int mid = left + (right - left) / 2;
                
                if(isPossible(mid)){
                    answer = mid;
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }
            
            return answer;
        }
        
        private boolean isPossible(int level){
            long totalTime = 0l;
            
            for(int i = 0; i < diffs.length; i++){
                int curDiff = diffs[i];
                int curTime = times[i];
                int prevTime = (i == 0) ? 0 : times[i - 1];
                
                if(level >= curDiff){
                    totalTime += curTime;
                }else{
                    long mistakes = (curDiff - level);
                    totalTime += mistakes * (curTime + prevTime) + curTime;
                }
                
                if(totalTime > limit) return false;
            }
            
            return true;
        }    
    }
}
