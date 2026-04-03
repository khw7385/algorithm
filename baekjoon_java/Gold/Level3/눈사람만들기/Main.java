package baekjoon_java.Gold.Level3.눈사람만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int n;
    private static int[] snow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        snow = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            snow[i] = Integer.parseInt(st.nextToken());
        }

        solve();
    }

    private static void solve(){
        Arrays.sort(snow);

        int diff = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            for(int j = i + 3; j < n; j++){
                int annaHeight = snow[i] + snow[j];

                int left = i + 1;
                int right = j - 1;

                while(left < right){
                    int elsaHeight = snow[left] + snow[right];

                    diff = Math.min(diff, Math.abs(annaHeight - elsaHeight));
                    if(elsaHeight < annaHeight) left++;
                    else if(elsaHeight > annaHeight) right--;
                    else break;
                }

                if(diff == 0) break;
            }
        }

        System.out.println(diff);
    }
}
