package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SS1206 {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++){
            int buildingNum = Integer.parseInt(br.readLine());
            int[] buildings = new int[buildingNum]; 
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < buildingNum; j++){
                buildings[j] = Integer.parseInt(st.nextToken());
            }

            solve(i, buildings);
        }
    }

    private static void solve(int testIdx, int[] buildings){
        int result = 0;

        for(int i = 2; i < buildings.length - 2; i++){
            if(buildings[i - 2] >= buildings[i]) continue;
            else if(buildings[i - 1] >= buildings[i]) continue;
            else if(buildings[i + 1] >= buildings[i]) continue;
            else if(buildings[i + 2] >= buildings[i]) continue;

            result += buildings[i] - Math.max(Math.max(buildings[i - 2], buildings[i - 1]), Math.max(buildings[i + 1], buildings[i + 2]));
        }

        System.out.println(String.format("#%d %d", testIdx + 1, result));
    }


}
