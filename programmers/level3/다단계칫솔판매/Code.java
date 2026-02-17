package programmers.level3.다단계칫솔판매;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code {

    public static void main(String[] args) {
        String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
        String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
        String[] seller = { "young", "john", "tod", "emily", "mary" };
        int[] amount = { 12, 4, 2, 5, 10 };

        int[] result = new Solution().solution(enroll, referral, seller, amount);
        System.out.println(Arrays.toString(result));
    }

    private static class Solution {
        private Map<String, Integer> nameIndexMap;
        private int[] parents;
        private int[] moneys;

        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amounts) {
            init(enroll, referral);

            for (int i = 0; i < seller.length; i++) {
                int money = amounts[i] * 100;

                int idx = nameIndexMap.get(seller[i]);
                moneys[idx] += (int) Math.ceil(money * 0.9);

                idx = parents[idx];
                money = (int) Math.floor(money * 0.1);

                while (idx >= 0 && money > 0) {
                    moneys[idx] += (int) Math.ceil(money * 0.9);

                    idx = parents[idx];
                    money = (int) Math.floor(money * 0.1);
                }
            }
            return moneys;
        }

        void init(String[] enroll, String[] referral) {
            nameIndexMap = new HashMap<>();
            parents = new int[enroll.length];
            moneys = new int[enroll.length];

            int index = 0;
            for (String name : enroll) {
                nameIndexMap.put(name, index++);
            }

            for (int i = 0; i < referral.length; i++) {
                parents[i] = nameIndexMap.getOrDefault(referral[i], -1);
            }
        }
    }
}
