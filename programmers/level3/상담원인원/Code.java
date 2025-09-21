package programmers.level3.상담원인원;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int k = 3;
        int n = 5;
        int[][] reqs = {
            { 10, 60, 1 }, 
            { 15, 100, 3 }, 
            { 20, 30, 1 }, 
            { 30, 50, 3 }, 
            { 50, 40, 1 }, 
            { 60, 30, 2 }, 
            { 65, 30, 1 }, 
            {70, 100, 2 }
        };

        int answer = solution.solution(k, n, reqs);

        System.out.println(answer);
    }

    public static class Solution{
        private int n, k;
        // 멘토 조합
        private List<int[]> mentorCombs = new ArrayList<>();
        private List<PriorityQueue<Integer>> pqs = new ArrayList<>(); // 제네릭은 일반 배열에 못 넣음.

        public int solution(int k, int n, int[][] reqs) {
            init(n, k);

            int[] comb = new int[k];
            combMentors(n, 0, comb);

            return calcLeastWaitingTime(reqs);
        }

        private void init(int n, int k){
            this.n = n;
            this.k = k;
        }

        // DFS로 조합 계산!
        private void combMentors(int remainingMentorCount, int type, int[] comb){       
            if(type == k - 1){
                comb[type] = remainingMentorCount;
                mentorCombs.add(comb.clone());
                return;
            }

            for(int i = 1; i < remainingMentorCount; i++){
                comb[type] = i;
                combMentors(remainingMentorCount - i, type + 1, comb);
            }
        }

        private int calcLeastWaitingTime(int[][] reqs){
            for(int i = 0; i < k; i++){
                pqs.add(new PriorityQueue<>());
            }

            int result = Integer.MAX_VALUE;

            for(int[] comb: mentorCombs){
                result = Math.min(result, calcWaitingTime(comb, reqs));
            }

            return result;
        }

        private int calcWaitingTime(int[] comb, int[][] reqs){
            for(int i = 0; i < k; i++){    
                PriorityQueue<Integer> pq = pqs.get(i);
                pq.clear();

                IntStream.range(0, comb[i])
                    .forEach((idx) -> pq.add(0));
            }

            int result = 0;
            
            for(int i = 0; i < reqs.length; i++){
                int[] req = reqs[i];

                int type = req[2] - 1;
                PriorityQueue<Integer> pq = pqs.get(type);

                int endTime = pq.poll();
                int startTime = req[0];

                if(endTime > req[0]){ 
                    result += endTime - req[0];
                    startTime = endTime;
                }
                pq.add(startTime + req[1]);
            }
            
            return result;
        }
    }
}
