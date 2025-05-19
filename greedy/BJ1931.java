    package greedy;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.Arrays;

    public class BJ1931{
        private static int N;
        private static Meeting[] meetings;
        private static int[] dp;
        private static int[] lastMeetings;

        public static void main(String[] args) throws IOException {
            readInput();
            solve2();
        }

        private static void readInput() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());

            meetings = new Meeting[N];
            dp = new int[N + 1];
            lastMeetings = new int[N + 1];

            for(int i = 0; i < N; i++){
                String[] meetingInfos = br.readLine().split(" ");
                meetings[i] = new Meeting(Integer.parseInt(meetingInfos[0]), Integer.parseInt(meetingInfos[1]));
            }
        }

        private static void solve(){
            // end 기준 오름차순 정렬
            Arrays.sort(meetings, (a, b) -> {
                if(a.end == b.end){
                    return a.start - b.start;
                }
                return a.end - b.end;
            });

            // 초기화
            dp[0] = 1;
            lastMeetings[0] = 0;

            for(int i = 1; i < N; i++){
                if(meetings[lastMeetings[i - 1]].end <= meetings[i].start){
                    dp[i] = dp[i - 1] + 1;
                    lastMeetings[i] = i;
                }
                else{
                    int j;
                    for(j = i - 1; j >= 0; j--){
                        if(meetings[lastMeetings[j]].end <= meetings[i].start) break;
                    }
                    if(j == -1 || dp[i - 1] >= dp[j] + 1){
                        dp[i] = dp[i - 1];
                        lastMeetings[i] = lastMeetings[i - 1];
                    }
                    else{
                        dp[i] = dp[j] + 1;
                        lastMeetings[i] = i;
                    }
                }
            }
            System.out.println(dp[N - 1]);
        }

        private static void solve2(){
            Arrays.sort(meetings, (a, b) -> {
                if(a.end == b.end) return a.start - b.start;
                return a.end - b.end;
            });

            int end = 0;
            int result = 0;

            for(int i = 0; i < meetings.length; i++){
                if(meetings[i].start >= end){
                    end = meetings[i].end;
                    result++;
                } 
            }

            System.out.println(result);
        }

        private static class Meeting{
            int start;
            int end;

            public Meeting(int start, int end){
                this.start = start;
                this.end = end;
            }
        }
    }
