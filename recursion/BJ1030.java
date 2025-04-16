package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1030 {
    private static int s, N, K, R1, R2, C1, C2;
    public static void main(String[] args) throws IOException{
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        s = Integer.parseInt(inputs[0]);
        N = Integer.parseInt(inputs[1]);
        K = Integer.parseInt(inputs[2]);
        R1 = Integer.parseInt(inputs[3]);
        R2 = Integer.parseInt(inputs[4]);
        C1 = Integer.parseInt(inputs[5]);
        C2 = Integer.parseInt(inputs[6]);
    }

    private static void solve(){
       StringBuilder sb =  new StringBuilder();
       for(int i = R1; i <= R2; i++){
            for(int j = C1; j <= C2; j++){
                sb.append(isBlack(s, i, j) ? "1" : "0");
            }
            sb.append("\n");
       } 

       System.err.print(sb.toString());
    }

    private static boolean isBlack(int level, int r, int c){
        if(level == 0) return false;

        int size = (int) Math.pow(N, level - 1);

        int blackStart = (N - K) /2;
        int blackEnd = blackStart + K - 1;

        int rowBlock = (r / size) % N;
        int colBlock = (c / size) % N;

        if(rowBlock >= blackStart && rowBlock <= blackEnd && colBlock >= blackStart && colBlock <= blackEnd){
            return true;
        }

        return isBlack(level - 1, r % size, c % size);
    }
}
