package baekjoon_java;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ1477 {
    private static int n, m, l;
    private static List<Integer> positions;

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
            m = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            positions = new ArrayList<Integer>();

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                positions.add(Integer.parseInt(st.nextToken()));
            }

            positions.add(0);
            positions.add(l);

        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private static int solve(){
        Collections.sort(positions);

            int start = 1;
            int end = l - 1;

            int max = start;
            while(start <= end){
                // 구간의 길이가 X 일 때 휴게소의 개수가 M개 보다 큰가 아닌가?

                int mid = (start + end) / 2;
                
                int cnt = 0;
                for(int i = 0; i < positions.size() - 1; i++){
                    cnt += (positions.get(i + 1) - positions.get(i) - 1) / mid;
                }

                if(cnt > m){
                    start = mid + 1;
                }else{
                    end = mid - 1;
                    max = mid;
                }
            }

        return max;
    }
}
