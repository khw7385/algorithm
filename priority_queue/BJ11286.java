package priority_queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ11286 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq =  new PriorityQueue<>((a, b) -> { 
            if(Math.abs(a) == Math.abs(b)){
                return a - b;
            }

            return Math.abs(a) - Math.abs(b);
        });

        for(int i = 0; i < n; i++){
            int x =  Integer.parseInt(br.readLine());
            if(x != 0) pq.add(x);
            else{
                if(pq.isEmpty()) System.out.println(0);
                else System.out.println(pq.poll());
            }
        }
    }
}
