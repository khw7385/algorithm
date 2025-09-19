import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PGSecretMap{
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        int[] arr1 = {9, 20, 28, 18, 11};
        int[] arr2 = {30, 1, 21, 17, 28};

        String[] answer = solution.solution(5, arr1, arr2);

        System.out.println();
    }

    public static class Solution{
        public String[] solution(int n, int[] arr1, int[] arr2){   
            List<String> answer = new ArrayList<>();
            
            int[][] transArr1 = transTo2digits(n, arr1);
            int[][] transArr2 = transTo2digits(n, arr2);

            IntStream.range(0, n)
                    .forEach(idx -> answer.add(compRow(transArr1[idx], transArr2[idx])));

            return answer.toArray(String[]::new);
        }

        private int[][] transTo2digits(int n, int[] arr){
            return IntStream.range(0, arr.length)
                        .mapToObj(idx -> transTo2digit(n, arr[idx]))
                        .toArray(int[][]::new);
        }

        private int[] transTo2digit(int n, int num){
            int[] resultArr = new int[n];
            int idx = n - 1;
            
            while(num != 0){ 
                resultArr[idx] = num % 2;
                num = num / 2;
                idx--;
            }
            return resultArr;
        }

        private String compRow(int[] row1, int[] row2){
            String result = "";
            for(int i = 0; i < row1.length; i++){
                if(row1[i] == 0 && row2[i] == 0) result += " ";
                else result += "#";
            }
            return result;
        }
    }

    public static class Solution2{
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] result = new String[n];
            for (int i = 0; i < n; i++) {
                result[i] = Integer.toBinaryString(arr1[i] | arr2[i]);
            }
    
            for (int i = 0; i < n; i++) {
                result[i] = String.format("%" + n + "s", result[i]);
                result[i] = result[i].replaceAll("1", "#");
                result[i] = result[i].replaceAll("0", " ");
            }
    
            return result;
        }
    }
}