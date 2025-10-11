package baekjoon_java;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BJ7785 {
    private static int n;
    private static Log[] logs;

    public static void main(String[] args) {
        init();
        List<String> answer = solve();
        output(answer);
    }

    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        try{
            n = Integer.parseInt(br.readLine());

            logs = new Log[n];
            for(int i = 0; i < n; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                logs[i] = new Log(st.nextToken(), st.nextToken());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static List<String> solve(){
        HashSet<String> names = new HashSet<>();

        for(int i = 0; i < n; i++){
            if(logs[i].record.equals("enter")) names.add(logs[i].name);
            else names.remove(logs[i].name);
        }

        List<String> notLeaveWorkers = names.stream().collect(Collectors.toList());
        Collections.sort(notLeaveWorkers, Collections.reverseOrder());
        return notLeaveWorkers;
    }

    private static void output(List<String> answer){
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            for (String name : answer) {
                bw.write(name + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static class Log{
        String name;
        String record;

        public Log(String name, String record){
            this.name = name;
            this.record = record;
        }
    }
}
