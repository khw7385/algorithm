package programmers.level3.표현가능한이진트리;

public class Code {
    public static void main(String[] args) {
        
    }
    
    static class Solution {
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];
            
            for(int i = 0; i < numbers.length; i++){
                if(isPossible(numbers[i])) answer[i] = 1;
                else answer[i] = 0;
            }
            return answer;
        }
        
        boolean isPossible(long number){
            String binaryString = Long.toBinaryString(number);
            String fullBinaryString = getFullBinaryString(binaryString);
            
            return dfs(fullBinaryString, 0, fullBinaryString.length() - 1);
        }
        
        String getFullBinaryString(String binaryString){
            int node = 1;
            int addedNodes = 2;
            
            if(node == binaryString.length()) return binaryString;
            
            while(true){
                if(node + addedNodes >= binaryString.length()) break;
                
                node += addedNodes;
                addedNodes *= 2;
            }
            
            StringBuilder sb = new StringBuilder("");
            
            int dummys = (node + addedNodes) - binaryString.length();
            
            for(int i = 0; i < dummys; i++){
                sb.append(" ");
            }
            
            return sb.toString() + binaryString;
        }
        
        boolean dfs(String binaryString, int startIdx, int endIdx){
            if(startIdx == endIdx){
                return true;
            }
            
            int parentIdx = (startIdx + endIdx) / 2;
            int leftChildIdx = (startIdx + parentIdx - 1) / 2;
            int rightChildIdx = (parentIdx + 1 + endIdx) / 2;
            
            if(binaryString.charAt(parentIdx) == 0){
                if(binaryString.charAt(leftChildIdx) == 1 || binaryString.charAt(rightChildIdx) == 1) return false;
            }
            
            return dfs(binaryString, startIdx, parentIdx - 1) && dfs(binaryString, parentIdx + 1, endIdx);
        }
    }
}
