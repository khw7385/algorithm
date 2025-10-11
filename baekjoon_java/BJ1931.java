package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class BJ1931 {
    private static int n;
    private static Meeting[] meetings;
    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            n = Integer.parseInt(br.readLine());
            
            meetings = new Meeting[n];

            IntStream.range(0, n)
                .forEach(idx -> {
                    try{
                        StringTokenizer st = new StringTokenizer(br.readLine());
                        int start = Integer.parseInt(st.nextToken());
                        int end = Integer.parseInt(st.nextToken());

                        meetings[idx] = new Meeting(start, end);
                    }catch(IOException e){
                        System.out.println(e.getLocalizedMessage());
                    }
                });
        }catch(IOException e){
            System.out.println(e.getMessage());
        }   
    }

    private static int solve(){
        Arrays.sort(meetings, (m1, m2) -> {
            if (m1.end == m2.end) return m1.start - m2.start;
            return m1.end - m2.end;
        });
        
        int count = 0;
        int time = 0;

        for(int i = 0; i < n; i++){
            if(meetings[i].start >= time){
                time = meetings[i].end;
                count++;
            }
        }

        return count;
    }


    static class Meeting{
        int start;
        int end;

        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
}
