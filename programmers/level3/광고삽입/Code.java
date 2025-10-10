package programmers.level3.광고삽입;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Code {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

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

    private static class Solution2{
        public String solution(String play_time, String adv_time, String[] logs) {
            int playTimeSeconds = changeTimeToInt(play_time);
            int advTimeSeconds = changeTimeToInt(adv_time);

            List<Event> events = new ArrayList<>();

            for(String log: logs){
                String[] logElements = log.split("-");
                
                int startTime = changeTimeToInt(logElements[0]);
                int endTime = changeTimeToInt(logElements[1]);

                events.add(new Event(startTime, 1));
                events.add(new Event(endTime, -1)); 
            }
            events.add(new Event(0, 0));

            Collections.sort(events);

            // 초기화
            int idx1 = 0, idx2 = 0; // 투 포인터 변수 정의: idx1(광고의 시작 시간보다 크거나 같은 이벤트 중 가장 가깝게 위치한 이벤트 인덱스), idx2(광고의 끝 시간보다 작거나 같은 이벤트 중 가장 가깝게 위치한 인덱스)
            int cnt1 = 0, cnt2 = 0;
            
            long curValue = 0, maxValue = 0; 
            int curTime = 0, maxTime = 0;

            while(idx2 + 1 < events.size() && events.get(idx2 + 1).timeSeconds <= advTimeSeconds){
                Event event1 = events.get(idx2);
                Event event2 = events.get(idx2 + 1);
                
                curValue = curValue + cnt2 * (event2.timeSeconds - event1.timeSeconds);
                cnt2 = cnt2 + event2.value;
                idx2++;
            }            

            curValue = (advTimeSeconds - events.get(idx2).timeSeconds) * cnt2;
            maxValue = curValue;

            // 투 포인터 방법을 활용한 풀이
            while(curTime + advTimeSeconds <= playTimeSeconds && (idx2 + 1) < events.size()){
                int delta1 = events.get(idx1 + 1).timeSeconds - curTime;
                int delta2 = events.get(idx2 + 1).timeSeconds - (curTime + advTimeSeconds);

                if(delta1 <= delta2){
                    curValue = curValue + (long)(cnt2 - cnt1) * delta1; 
                    cnt1 += events.get(idx1 + 1).value;
                    idx1++;
                    curTime += delta1;
                }else{
                    curValue = curValue + (long)(cnt2 - cnt1) * delta2;
                    cnt2 += events.get(idx2 + 1).value;
                    idx2++;
                    curTime += delta2;
                }

                if(curValue > maxValue){
                    maxValue = curValue;
                    maxTime = curTime;
                }
            }
            
            return changeSecondsToTimeString(maxTime);
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

        static class Event implements Comparable<Event>{
            int timeSeconds;
            int value;

            public Event(int timeSeconds, int value){
                this.timeSeconds = timeSeconds;
                this.value = value;
            }

            @Override
            public int compareTo(Event e) {
                if(this.timeSeconds > e.timeSeconds) return 1;
                if (this.timeSeconds < e.timeSeconds) return -1;
                if(this.value > e.value) return 1;
                if(this.value == e.value) return 0;
                return -1;
            }
        }
    }
}
