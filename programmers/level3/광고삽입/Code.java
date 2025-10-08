package programmers.level3.광고삽입;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        String play_time = "99:59:59";
        String adv_time = "25:00:00";
        String[] logs = { "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00" };
        
        String answer = solution.solution(play_time, adv_time, logs);

        System.out.println(answer);
    }
    
    private static class Solution{
        public String solution(String play_time, String adv_time, String[] logs) {
            int playTimeSeconds = changeTimeToInt(play_time);
            int advTimeSeconds = changeTimeToInt(adv_time);

            int[] usersByTime = new int[playTimeSeconds + 1];

            for(String log : logs){
                String[] logElements = log.split("-");
                
                int startTime = changeTimeToInt(logElements[0]);
                int endTime = changeTimeToInt(logElements[1]);

                usersByTime[startTime] += 1;
                usersByTime[endTime] -= 1;
            }

            long[] watchingTimeArr = new long[playTimeSeconds + 1];

            int userCount = 0;

            for (int i = 0; i < playTimeSeconds; i++) {
                userCount += usersByTime[i];
                watchingTimeArr[i + 1] = watchingTimeArr[i] + userCount;
            }
            
            long maxAdvTime = watchingTimeArr[advTimeSeconds];
            int result = 0;

            for (int startTime = 1; startTime + advTimeSeconds <= playTimeSeconds; startTime++) {
                int endTime = startTime + advTimeSeconds;
                long current = watchingTimeArr[endTime] - watchingTimeArr[startTime];
                if (current > maxAdvTime) {
                maxAdvTime = current;
                result = startTime;
                }
            }

            return changeSecondsToTimeString(result);
        }


        private int changeTimeToInt(String time){
            String[] timeElements = time.split(":");

            int timeIntValue = 0;
            timeIntValue += Integer.parseInt(timeElements[0]) * 3600;
            timeIntValue += Integer.parseInt(timeElements[1]) * 60;
            timeIntValue += Integer.parseInt(timeElements[2]);

            return timeIntValue;
        }

        private String changeSecondsToTimeString(int seconds){
            int hours = seconds / 3600;
            seconds = seconds % 3600;

            int miniutes = seconds / 60;
            seconds = seconds % 60;

            return String.format("%02d:%02d:%02d", hours, miniutes, seconds);
        }
    }
}
