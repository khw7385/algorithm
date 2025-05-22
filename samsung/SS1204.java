package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SS1204{
    private static String numString; 

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());

        for(int i = 0; i < testNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            numString = st.nextToken();

            int swapNum = Integer.parseInt(st.nextToken());
            solve(i, swapNum);
        }
    }

    private static void solve(int testIdx, int swapNum){
        Set<String> cur = new HashSet<>();
        cur.add(numString);

        for(int i = 0; i < swapNum; i++){
            Set<String> next = new HashSet<>();

            for(String str:cur){
                char[] charArr = str.toCharArray();

                for(int j = 0; j < charArr.length; j++){
                    for(int k = j + 1; k < charArr.length; k++){
                        swap(charArr, j, k);
                        next.add(new String(charArr));
                        swap(charArr, k, j);
                    }
                }
            }

            cur = next;
        }
        int result = 0;

        for(String str: cur){
            result = Math.max(result, Integer.parseInt(str));
        }

        System.out.println(String.format("#%d %d", testIdx + 1, result));
    }

    private static void swap(char[] numbers, int a, int b){
        char temp = numbers[a];
        numbers[a] = numbers[b];
        numbers[b] = temp;
    }
}