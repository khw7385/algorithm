package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ15686{
    private static int N, M;
    private static int[][] map;
    private static List<Point> house = new ArrayList<>();
    private static List<Point> chickens = new ArrayList<>();
    private static int[] chickenDistances;
    private static int result = 100 * 2 * 50 * 13;

    public static void main(String[] args) throws IOException{
        readInput();
        solve2();
    }    

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2){
                    chickens.add(new Point(i, j));
                }
                else if(map[i][j] == 1){
                    house.add(new Point(i, j));
                }
            }
        }

        chickenDistances = new int[house.size()];
        chickenDistances2 = new int[1 << chickens.size()][house.size()];
    }

    private static void solve(){
        for(int status = 0; status < (1 << chickens.size()); status++){
            if(!checkStatus(status)) continue;

            for(int i = 0; i < house.size(); i++){
                chickenDistances[i] = 100;
            }

            int temp = status;
            for(int i = 0; i < chickens.size(); i++){
                int isSelected = temp % 2;
                temp /= 2;

                if(isSelected == 1){
                    for(int j = 0; j < house.size(); j++){
                        Point housePoint = house.get(j);
                        Point chickenPoint = chickens.get(i);
                        chickenDistances[j] = Math.min(chickenDistances[j], getChickenDistance(housePoint, chickenPoint));
                    }
                }
            }
            result = Math.min(result, getChickenDistanceOfCity(chickenDistances));
        }

        System.out.println(result);
    }

    private static boolean checkStatus(int status){
        int cnt = 0;
        for(int i = 0; i < 13; i++){
            int isSelected = status % 2;
            status /= 2;
            if(isSelected == 1) cnt++;
        }
        return cnt <= M;
    }

    private static int getChickenDistance(Point p1, Point p2){
        return (Math.max(p1.x, p2.x) - Math.min(p1.x, p2.x)) + (Math.max(p1.y, p2.y) - Math.min(p1.y, p2.y));
    }

    private static int getChickenDistanceOfCity(int[] chickenDistances){
        int sum = 0;
        for(int i = 0; i < house.size(); i++){
            sum += chickenDistances[i];
        }
        return sum;
    }

    // 시간 복잡도: 2 ^ 13(치킨 집의 수) * 100 
    private static void solve2(){
        // 치킨 거리 초기화
        for(int i = 0 ; i < (1 << chickens.size()); i++){
            for(int j = 0; j < house.size(); j++){
                chickenDistances2[i][j] = 100;
            }
        }
        // for(int i = 0; i < house.size(); i++){
        //     chickenDistances[i] = 100;
        // }
        
        dfs(0, 0, 0);
        System.out.println(result);
    }
    private static int[][] chickenDistances2;

    private static void dfs(int idx, int num, int status){
        // 종료 조건
        if(num == M){
            result = Math.min(result, getChickenDistanceOfCity(chickenDistances2[status]));
            return;
        }
        

        for(int i = idx; i < chickens.size(); i++){
            int nextStatus = status | (1 << i);
            for(int j = 0 ; j < house.size(); j++){
                chickenDistances2[nextStatus][j] = Math.min(chickenDistances2[status][j], getChickenDistance(house.get(j), chickens.get(i)));
            }
            dfs(idx + 1, num + 1, nextStatus);
        }
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}