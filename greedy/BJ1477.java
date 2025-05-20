package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ1477 {
    private static int N, M, L;
    private static int[] locations;
    private static int[] distances;
    public static void main(String[] args) throws IOException {
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        locations = new int[N + 2];
        distances = new int[N + 1];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            locations[i] = Integer.parseInt(st.nextToken());
        }
        locations[N + 1] = L;
    }

    private static void solve(){
        Arrays.sort(locations);

        // 알고리즘의 종류와 상관없이 구간의 길이는 계산되어야 한다.
        PriorityQueue<Section> pq = new PriorityQueue<>((a, b) -> {
            return b.distance - a.distance;
        });

        // 초기화
        for(int i = 0; i <= N; i++){
            distances[i] = locations[i + 1] - locations[i];
            pq.add(new Section(i, 1, distances[i]));
        }

        for(int i = 0; i < M; i++){
            Section s = pq.poll();
            int distance = distances[s.idx]/(s.num + 1) + (distances[s.idx] % (s.num + 1) == 0 ? 0 : 1);
            pq.add(new Section(s.idx, s.num + 1, distance));
        }

        System.out.println(pq.poll().distance);
    }

    private static class Section{
        private int idx;
        private int num;
        private int distance;

        public Section(int idx, int num, int distance){
            this.idx = idx;
            this.num = num;
            this.distance = distance;
        }
    }
}
