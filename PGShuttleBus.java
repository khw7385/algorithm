import java.util.Arrays;

class PGShuttleBus{
    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 10;
        int t = 60;
        int m = 45;
        String[] timetable = {
            "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"
        };

        String answer = solution.solution(n, t, m, timetable);

        System.out.println(answer);
    }

    private static class Solution{
        public String solution(int n, int t, int m, String[] timetable) {
            int[] newTimeTable = new int[timetable.length];

            // 시간 변환
            for(int i = 0 ; i < timetable.length; i++){
                newTimeTable[i] = transTimeToMinInteger(timetable[i]);
            }

            // 정렬
            Arrays.sort(newTimeTable);

            int startTime = 9 * 60;
            int time = startTime; // 9시
            int crewIndex = 0;

            boolean canBoard = false; 
            for(int i = 0; i < n - 1; i++){
                time = startTime + i * t;
                
                int num = 0;
                
                // 각 타임마다 
                while(num < m){
                    if(crewIndex == newTimeTable.length - 1) break;
                    if(newTimeTable[crewIndex] > time){
                        break;
                    }
                    num++;
                    crewIndex++;
                }
            }

            time = startTime + (n - 1) * t;
            int num = 0;
            while(num < m){
                if(crewIndex == newTimeTable.length){
                    canBoard = true;
                    break;
                }
                if(newTimeTable[crewIndex] > time){
                    canBoard = true;
                    break;
                }
                num++;
                crewIndex++;
            }

            int result;
            if(canBoard){
                result = startTime + (n - 1) * t;
            }else{
                result = newTimeTable[crewIndex - 1] - 1;
            }

            return transMinToTimeString(result);
        }

        private int transTimeToMinInteger(String time){
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3));

            return hour * 60 + min;
        }

        private String transMinToTimeString(int minTime){
            int hour = minTime / 60;
            int min = minTime % 60;

            return String.format("%02d:%02d", hour, min);
        }
    }
}