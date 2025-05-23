package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SS2805 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());
        for(int i = 0; i < testNum; i++){
            int N = Integer.parseInt(br.readLine());

            int result = 0;

            for(int j = 0; j < N; j++){
                String row = br.readLine();
                char[] farms = row.toCharArray();
                if(j <= N / 2){
                    for(int k = N / 2 - j; k <= N / 2 + j; k++){
                        result += farms[k] - '0';
                    }
                }else{
                    for(int k = j - N / 2; k < N - (j - N / 2); k++){
                        result += farms[k] - '0';
                    }
                }
            }

            System.out.println(String.format("#%d %d", i + 1, result));
        }
    }
}
