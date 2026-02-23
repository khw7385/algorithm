package baekjoon_java.Gold.Level4.좋은친구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]);
        int k = Integer.parseInt(nk[1]);

        Queue<Integer>[] queues = new LinkedList[21];

        for (int i = 1; i <= 20; i++) {
            queues[i] = new LinkedList<>();
        }

        long count = 0;

        for (int i = 0; i < n; i++) {
            int length = br.readLine().trim().length();

            while (!queues[length].isEmpty() && i - queues[length].peek() > k) {
                queues[length].poll();
            }

            count += queues[length].size();
            queues[length].add(i);
        }

        System.out.println(count);
    }
}
