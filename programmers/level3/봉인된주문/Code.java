package programmers.level3.봉인된주문;

import java.util.Arrays;

public class Code {
    public static void main(String[] args) {
        
        Solution solution = new Solution();

        int n = 7388;
        String[] bans = { "gqk", "kdn", "jxj", "jxi", "fug", "jxg", "ewq", "len", "bhc" };
        String answer = solution.solution(n, bans);

        System.out.println(answer);
    }

    private static class Solution{
        private static final int ALPHABET_LENGTH = 26;

        public String solution(long n, String[] bans) {
            long order = n;

            Arrays.sort(bans, (String str1, String str2) -> {
                if(str1.length() != str2.length()) return str1.length() - str2.length();
                return str1.compareTo(str2);
            });

            for(int i = 0 ; i < bans.length; i++){
                String ban = bans[i];

                long banOrder = calculateBanOrder(ban);

                if(banOrder <= order){
                    order++;
                }else break;
            }
        
            return calculationBan(order);
        }

        private long calculateBanOrder(String ban){
            long order = 0;

            for(int i = 0 ; i < ban.length(); i++){
                char ch = ban.charAt(i);
                
                order = order * ALPHABET_LENGTH + (ch - 'a') + 1;
            }
            return order;
        }

        private String calculationBan(long order){
            String ban = "";

            while(order != 0){
                order--;
                String ch = String.valueOf((char)('a' + (int)((order) % ALPHABET_LENGTH)));

                ban = ch + ban;
                order = order / ALPHABET_LENGTH;
            }

            return ban;
        }
    }
}
