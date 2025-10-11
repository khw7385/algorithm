package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1074 {
    private static int n, r, c;
    public static void main(String[] args) {
        init();
        int result = solve();
        System.out.println(result);
    }
    
    private static void init(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            n = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
        }catch(IOException e){
            System.out.println("error");
        }
    }

    private static int solve(){
        return visit(n, r, c);
    }

    private static int visit(int d, int row, int col){
        // 종료 조건
        if(d == 1) return row * 2 + col;
        
        // idx 다시 계산
        int len = 1 << (d - 1);

        int sectorRow = row / len;
        int sectorCol = col / len;
        int nextRow = row % len;
        int nextCol = col % len;

        return (len * len) * (sectorRow * 2 + sectorCol) + visit(d - 1, nextRow, nextCol);
    }
}
