package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ1935 {
    private static int n;
    private static String postFixNotation;
    private static char[] postFixNotationArray;
    private static int[] operands;

    public static void main(String[] args) throws IOException{
        input();
        solve();
    }

    private static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        postFixNotation = br.readLine();
    
        postFixNotationArray = postFixNotation.toCharArray();
        int operandCount = 0;

        for (char c : postFixNotationArray) {
            operandCount = Math.max(operandCount, c - 65 + 1);
        }
        operands = new int[operandCount];

        for(int i = 0; i < operandCount; i++){
            operands[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void solve(){
        Stack<Double> stack = new Stack<Double>();

        for (char c : postFixNotationArray) {
            
            if(c >= 'A' && c <= 'Z'){
                stack.push((double)operands[c - 65]);
            }else{
                double operand2 = stack.pop();
                double operand1 = stack.pop();
                
                if(c == '+'){
                    stack.push(operand1 + operand2);
                }else if(c == '-'){
                    stack.push(operand1 - operand2);
                }else if(c == '*'){
                    stack.push(operand1 * operand2);
                }else if(c == '/'){
                    stack.push(operand1 / operand2);
                }
            }
        }

        System.out.println(String.format("%.2f", stack.pop()));
    }
}
