package programmers.level3.에어컨;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int temperature = -10;
        int t1 = -5;
        int t2 = 5;
        int a = 5;
        int b = 1;
        int[] onboard = { 0, 0, 0, 0, 0, 1, 0 };

        int answer = solution.solution(temperature, t1, t2, a, b, onboard);
        System.out.println(answer);
    }

    static class Solution{
        private int temperature, t1, t2, a, b;
        private int[] onboard;
        private int[][] cache;

        private final int TEMP_OFFSET = 10;
        private final int MAX_TEMPERATURE = 40 + TEMP_OFFSET;
        private final int INF = 10000000;

        public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard){
            init(temperature, t1, t2, a, b, onboard);
            // int result = solveUsingBottomUpDp();

            int result = dfs(0, temperature + TEMP_OFFSET);
            return result;
        }

        private void init(int temperature, int t1, int t2, int a, int b, int[] onboard){
            this.onboard = onboard.clone();
            this.temperature = temperature;
            this.t1 = t1;
            this.t2 = t2;
            this.a = a;
            this.b = b;

            this.cache = new int[onboard.length][MAX_TEMPERATURE + 1];

            for(int i = 0; i < cache.length; i++){
                Arrays.fill(cache[i], -1);
            }
        }

        private int dfs(int time, int temp){
            int realTemp = temp - TEMP_OFFSET;

            // 종료 조건
            if(time == onboard.length - 1){
                if(onboard[time] == 1 && (realTemp < t1 || realTemp > t2)) return INF;
                return 0;
            }
            if(onboard[time] == 1 && (realTemp < t1 || realTemp > t2)) return INF;
            if(cache[time][temp] != -1) return cache[time][temp];

            int result = INF;

            // 에어컨 X
            if(realTemp == temperature) result = Math.min(result, dfs(time + 1, temp));
            else if(realTemp > temperature) result = Math.min(result, dfs(time + 1, temp - 1));
            else result = Math.min(result, dfs(time + 1, temp + 1));

            // 에어컨
            if(temp > 0) result = Math.min(result, dfs(time + 1, temp - 1) + a);
            if(temp < MAX_TEMPERATURE - 1) result = Math.min(result, dfs(time + 1, temp + 1) + a);
            result = Math.min(result, dfs(time + 1, temp) + b);

            return cache[time][temp] = result;
        }

        private int solveUsingBottomUpDp(){
            final int MAX_TEMPERATURE = 50;
            final int OFFSET = 10;
            final int INF = 1000000;

            int[][] dpArr = new int[onboard.length + 1][MAX_TEMPERATURE + 1];

            for(int[] row: dpArr){
                Arrays.fill(row, INF);
            }

            dpArr[0][temperature + OFFSET] = 0;

            for(int i = 0; i < onboard.length; i++){
                for(int temp = 0; temp < MAX_TEMPERATURE; temp++){

                    if(dpArr[i][temp] == INF) continue;

                    int realTemp = temp - OFFSET;

                    // 탑승 중인데 현재 온도가 쾌적한 실내 온도 범위에 포함되지 않으면 제외시킨다.
                    if(onboard[i] == 1 && (realTemp < t1 || realTemp > t2)) continue; 

                    // 에어컨 꺼짐
                    int nextTemp;

                    if(realTemp > temperature){
                        nextTemp = temp - 1;
                    }else if(realTemp < temperature){
                        nextTemp = temp + 1;
                    }else{
                        nextTemp = temp;
                    }

                    dpArr[i + 1][nextTemp] = Math.min(dpArr[i + 1][nextTemp], dpArr[i][temp]);

                    // 에어컨 켜짐
                    // 온도 유지
                    dpArr[i + 1][temp] = Math.min(dpArr[i + 1][temp], dpArr[i][temp] + b);

                    // 온도 내려감
                    if(temp > 0){
                        dpArr[i + 1][temp - 1] = Math.min(dpArr[i + 1][temp - 1], dpArr[i][temp] + a);
                    }

                    // 온도 올라감
                    if(temp < MAX_TEMPERATURE){
                        dpArr[i + 1][temp + 1] = Math.min(dpArr[i + 1][temp + 1], dpArr[i][temp] + a);
                    }
                }
            }

            int result = dpArr[onboard.length][0];

            for(int temp = 1; temp < MAX_TEMPERATURE; temp++){
                result = Math.min(result, dpArr[onboard.length][temp]);
            }

            return result;
        }
    }
}
