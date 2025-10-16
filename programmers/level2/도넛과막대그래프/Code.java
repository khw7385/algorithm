package programmers.level2.도넛과막대그래프;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] edges = {
            {2, 3}, {4, 3}, {1, 1}, {2, 1}
        };

        int[] answer = solution.solution(edges);
        System.out.println(Arrays.toString(answer));
    }

    static class Solution {
        int n;
        Node[] nodes = new Node[1000001];
        
        int notGraphNode = 0;

        public int[] solution(int[][] edges) {
            int[] answer = new int[4];
            init(edges);

            for(int i = 1; i <= n; i++){
                Node node = nodes[i];

                if (node.in == 0 && node.out >= 2){
                    notGraphNode = i;
                }
                else if(node.in >= 2 && node.out >= 2){
                    answer[3]++;
                }
                else if(node.out == 0 && node.in >= 1){
                    answer[2]++;
                }
            }

            answer[0] = notGraphNode;
            answer[1] = nodes[notGraphNode].out - answer[2] - answer[3];

            return answer;
        }

        private void init(int[][] edges){
            for(int i = 0; i <= 1000000; i++){
                nodes[i] = new Node();
            }

            for(int i = 0; i < edges.length; i++){
                Node startNode = nodes[edges[i][0]];
                Node endNode = nodes[edges[i][1]];                
                startNode.out++;
                endNode.in++;

                n = Math.max(n, Math.max(edges[i][0], edges[i][1]));
            }
        }

        class Node{
            int in; // 진입 차수
            int out; // 진입 차수

            Node(){
                this.in = 0;
                this.out = 0;
            }
        }
    }
}
