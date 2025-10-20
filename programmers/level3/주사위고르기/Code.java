package programmers.level3.주사위고르기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Code {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] dices = {
            { 1, 2, 3, 4, 5, 6 },
            { 3, 3, 3, 3, 4, 4 },
            { 1, 3, 3, 4, 4, 4 },
            { 1, 1, 4, 4, 5, 5 }
        };

        int[] answer = solution.solution(dices);

        answer = Arrays.stream(answer)
            .map(elem -> elem + 1)
            .toArray();

        System.out.println(Arrays.toString(answer));
    }

    static class Solution{
        int diceCount;
        
        List<int[]> diceCombs = new ArrayList<>();
        List<List<Integer>> diceCombCaseSums = new ArrayList<>();

        int[] tempSelectedDice;

        public int[] solution(int[][] dice) {
            int[] answer = {};
            
            init(dice);
            selectDice(0, 0);
            getCombCasesSums(dice);

            int maxWin = 0;
            int maxIdx = 0;
            
            for(int i = 0; i < diceCombCaseSums.size() / 2; i++){
                List<Integer> combCaseSums1 = diceCombCaseSums.get(i);
                List<Integer> combCaseSums2 = diceCombCaseSums.get(diceCombCaseSums.size() - 1 - i);
                
                Collections.sort(combCaseSums1);
                Collections.sort(combCaseSums2);

                int win = 0;

                for(int j = 0; j < combCaseSums1.size(); j++){
                    win += lowerBound(combCaseSums1.get(j), combCaseSums2);
                }

                if(win > maxWin){
                    maxWin = win;
                    maxIdx = i;
                }

                win = 0;
                for(int j = 0; j < combCaseSums2.size(); j++){
                    win += lowerBound(combCaseSums1.get(j), combCaseSums2);
                }

                if(win > maxWin){
                    maxWin = win;
                    maxIdx = diceCombCaseSums.size() - 1 - i;
                }
            }

            return Arrays.stream(diceCombs.get(maxIdx))
                        .map(elem -> elem + 1)
                        .toArray();
        }

        void init(int[][] dice){
            this.diceCount = dice.length;
            this.tempSelectedDice = new int[diceCount / 2];
        }     
        
        void selectDice(int idx, int count){
            if(count == diceCount / 2){
                diceCombs.add(Arrays.copyOf(tempSelectedDice, count));
                return;
            }

            for(int i = idx; i < diceCount; i++){
                tempSelectedDice[count] = i;
                selectDice(i + 1, count + 1);
            }
        }

        void getCombCasesSums(int[][] dices){
            for(int i = 0; i < diceCombs.size(); i++){
                int[] comb = diceCombs.get(i);

                List<Integer> combSums = new ArrayList<>();

                calcCombCaseSum(0, comb, dices, 0, combSums);
                diceCombCaseSums.add(combSums);
            }
        }

        void calcCombCaseSum(int idx, int[] comb, int[][] dices, int sum, List<Integer> sums){
            if(idx == comb.length){
                sums.add(sum);
                return;
            }

            for(int i = 0; i < 6; i++){
                calcCombCaseSum(idx + 1, comb, dices, sum + dices[comb[idx]][i], sums);
            }
        }

        int lowerBound(int num, List<Integer> arr){
            int start = 0;
            int end = arr.size();

            while(start < end){           
                int mid = (start + end) / 2;
                if(arr.get(mid) >= num){
                    end = mid;
                }else{
                    start = mid + 1;
                }
            }

            
            return start;
        }
    }
}



