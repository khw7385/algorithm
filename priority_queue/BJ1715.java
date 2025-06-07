package priority_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ1715{
    private static int n;
    private static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }
    }

    private static void solve(){
        int result = 0;
        while(true){
            int first = pq.poll();
            if(pq.isEmpty()) break;
            
            int second = pq.poll();

            result += first + second;

            pq.add(first + second);
        }

        System.out.println(result);
    }
}