package programmers.level3.징검다리건너기;

public class Code {
    public static void main(String[] args) {
        
    }
    static class Solution {
        public int solution(int[] stones, int k) {
            int left = 1;
            int right = 0;

            for (int stone : stones) {
                if (stone > right) {
                    right = stone;
                }
            }

            int answer = 0;

            while (left <= right) {
                int mid = (left + right) / 2;

                if (canCross(stones, k, mid)) {
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            return answer;
        }

        private boolean canCross(int[] stones, int k, int friends) {
            int consecutiveZeros = 0;

            for (int stone : stones) {
                if (stone < friends) {
                    consecutiveZeros++;
                    if (consecutiveZeros >= k) {
                        return false;
                    }
                } else {
                    consecutiveZeros = 0;
                }
            }

            return true;
        }
    }
}
