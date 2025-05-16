package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ12100{
    private static int N;
    private static int[][] map, copyMap;
    private static int result = 0;
    public static void main(String[] args)throws IOException {
        readInput();
        solve();
    }

    private static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solve(){
        for(int status = 0; status < (1 << 10); status++){
            int temp = status;

            copyMap = new int[map.length][map[0].length];

            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){
                    copyMap[i][j] = map[i][j];
                }
            }

            for(int i = 0; i < 5; i++){
                int dir = temp % 4;
                temp /= 4;
                move(dir);
            }
            getMaxResult();
        }

        System.out.println(result);
    }

    private static int getMaxResult(){
        int value = 0;
        for(int i = 0 ; i < copyMap.length; i++){
            for(int j = 0; j < copyMap[0].length; j++){
                value = Math.max(value, copyMap[i][j]);
            }
        }
        result = Math.max(result, value);
        return value;
    }

    private static void move(int dir){
        if(dir == 0){
            for(int i = 0; i < N; i++){
                int[] tempArr = new int[N];
                int idx = 0;
                for(int j = N - 1; j >= 0; j--){
                    if(copyMap[j][i] == 0) continue;
                    else if(tempArr[idx] == copyMap[j][i]) tempArr[idx++] = copyMap[j][i] * 2;
                    else if(tempArr[idx] == 0) tempArr[idx] = copyMap[j][i];
                    else tempArr[++idx] = copyMap[j][i];

                }

                for(int j = N - 1; j >= 0; j--){
                    copyMap[j][i] = tempArr[N - 1 -j];
                }
            }
        }
        else if(dir == 1){
            for(int i = 0; i < N; i++){
                int[] tempArr = new int[N];
                int idx = 0;
                for(int j = N - 1; j >= 0; j--){
                    if(copyMap[i][j] == 0) continue;
                    else if(tempArr[idx] == copyMap[i][j]) tempArr[idx++] = copyMap[i][j] * 2;
                    else if(tempArr[idx] == 0) tempArr[idx] = copyMap[i][j];
                    else tempArr[++idx] = copyMap[i][j];
                }

                for(int j = N - 1; j >= 0; j--){
                    copyMap[i][j] = tempArr[N - 1 -j];
                }
            }
        }
        else if(dir == 2){
            for(int i = 0; i < N; i++){
                int[] tempArr = new int[N];
                int idx = 0;
                for(int j = 0; j < N; j++){
                    if(copyMap[j][i] == 0) continue;
                    else if(tempArr[idx] == copyMap[j][i]) tempArr[idx++] = copyMap[j][i] * 2;
                    else if(tempArr[idx] == 0) tempArr[idx] = copyMap[j][i];
                    else tempArr[++idx] = copyMap[j][i];
                }

                for(int j = 0; j < N; j++){
                    copyMap[j][i] = tempArr[j];
                }
            }
        }
        else if(dir == 3){
            for(int i = 0; i < N; i++){
                int[] tempArr = new int[N];
                int idx = 0;
                for(int j = 0; j < N; j++){
                    if(copyMap[i][j] == 0) continue;
                    else if(tempArr[idx] == copyMap[i][j]) tempArr[idx++] = copyMap[i][j] * 2;
                    else if(tempArr[idx] == 0) tempArr[idx] = copyMap[i][j];
                    else tempArr[++idx] = copyMap[i][j];
                }

                for(int j = 0; j < N; j++){
                    copyMap[i][j] = tempArr[j];
                }
            }
        }
    }
}


                    // for(int k = j; k < copyMap.length - 1; k++){
                    //     if(copyMap[k + 1][i] == 0){
                    //         copyMap[k + 1][i] = copyMap[k][i];
                    //         copyMap[k][i] = 0;
                    //         continue;
                    //     }
                    //     else if(!isMerged[k + 1] && copyMap[k + 1][i] == copyMap[k][i]){
                    //         copyMap[k + 1][i] = copyMap[k][i] * 2;
                    //         copyMap[k][i] = 0;
                    //         isMerged[k + 1] = true;
                    //     }
                    //     break;
                    // }