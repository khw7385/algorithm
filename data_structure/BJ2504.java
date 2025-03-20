package data_structure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ2504{
    private static String input;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
    }

    private static void solve(){
        boolean isCorrect = true;

        Stack<String> stack = new Stack<>();
        
        for(int i = 0; i < input.length(); i++){
            char ch = input.charAt(i);

            if(ch == '(' || ch == '['){
                stack.push(String.valueOf(ch));          
            }else{
                int num = 0;
                
                while(!stack.isEmpty() && isInteger(stack.peek())){
                    num += Integer.parseInt(stack.pop());
                }
                
                // 스택이 비어 있는 경우
                if(stack.isEmpty()){
                    isCorrect = false;
                    break;
                }
                String top = stack.pop();
                
                // 스택에 숫자가 없는 경우(곱하기 연산을 위해서)
                if(num == 0) num = 1;

                if(ch == ']' && top.equals("[")){
                    num *= 3;
                    stack.push(String.valueOf(num));
                }else if(ch == ')' && top.equals("(")){
                    num *= 2;
                    stack.push(String.valueOf(num));
                }else{
                    isCorrect = false;
                    break;
                }
            }
        }
        int result = 0;

        while(!stack.isEmpty()){
            String top = stack.pop();

            if(top.equals("[") || top.equals("(")){
                isCorrect = false;
                break;
            }

            result += Integer.parseInt(top);
        }

        if(isCorrect){
            System.out.println(result);
        }else{
            System.out.println(0);
        }
    }

    private static boolean isInteger(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}