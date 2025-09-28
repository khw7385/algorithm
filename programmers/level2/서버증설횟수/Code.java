package programmers.level2.서버증설횟수;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] player = { 0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0 };
        int m = 5;
        int k = 1;
        int answer = solution.solution(player, m, k);

        System.out.println(answer);
    }

    private static class Solution{
        private int[] player;
        private int m, k;

        public int solution(int[] player, int m, int k){
            init(player, m, k);
            int answer = solve();
            return answer;
        }

        private void init(int[] player, int m, int k){
            this.player = player.clone();
            this.m = m;
            this.k = k;
        }

        private int solve(){
            int playerCount = player.length;

            int curServerCount = 0;
            int scaleOutServerCount = 0;
            
            ServerScaleOut[] scaleOuts = new ServerScaleOut[24];
            int scaleOutIdx = 0;

            for(int i = 0; i < playerCount; i++){
                int neededServerCount = player[i] / m;

                if(neededServerCount > curServerCount){
                    scaleOutServerCount += neededServerCount - curServerCount;
                    ServerScaleOut newServerScaleOut = new ServerScaleOut(neededServerCount - curServerCount, k);
                    scaleOuts[scaleOutIdx++] = newServerScaleOut;
                    curServerCount = curServerCount + neededServerCount - curServerCount;
                }

                int curIdx = 0;
                
                for(int j = 0; j < scaleOutIdx; j++){
                    ServerScaleOut info = scaleOuts[j];
                    
                    if(info.time == 1){
                        curServerCount = curServerCount - info.count;
                    } 
                    else{
                        info.time--;
                        
                        if(j != curIdx){
                            scaleOuts[curIdx] = info;
                        }

                        curIdx++;
                    }
                }
                scaleOutIdx = curIdx;
            }
            
            return scaleOutServerCount;
        }

        class ServerScaleOut{
            int count;
            int time;

            public ServerScaleOut(int count, int time){
                this.count = count;
                this.time = time;
            }
        }


    }
}
