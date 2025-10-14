package programmers.level2.지게차와크레인;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Code {
    public static void main(String[] args) {
        String[] storage = {
            "AZWQY", "CAABX", "BBDDA", "ACACA"
        };

        String[] requests = {
           "A", "BB", "A"
        };
        
        Solution solution = new Solution();
        int answer = solution.solution(storage, requests);
        System.out.println(answer);
    }   

    static class Solution{
        int n, m;
        int[][] status; // 0: 외곽, 1: 단순 빈 공간, 2: 일반 컨테이너, -1: 셋 다 아님 
        int[] dx = { 1, 0, -1, 0};
        int[] dy = { 0, 1, 0, -1};

        public int solution(String[] storage, String[] requests) {
            init(storage);

            int result = 0;
            for(int i = 0; i < requests.length; i++){
                String request = requests[i];
                result += countRemovedContainer(storage, request);
            }

            return n * m - result;
        }

        void init(String[] storage){    
            this.n = storage.length;
            this.m = storage[0].length();

            status = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(isOutside(i, j)) status[i][j] = 0;
                    else status[i][j] = 2;
                }
            }
        }
        int countRemovedContainer(String[] storage, String request){
            // request의 길이가 2라면
            // request의 길이가 1이라면
            
            // 둘다 순회를 하면서 해당하는 값이 외곽인지를 검사
                // 외곽인지를 검사하는 방법:
                    // 탐색하면서 검사: 이미 결정된 모양인데 탐색하면서 외곽인지를 검사하는 것은 중복된 연산이다.
                    // 미리 결정 후 제거 할 때마다 외곽의 범위 변경!
                        // 상태값으로 표시 외곽: 0, 단순 빈 공간: 1, 둘 다 아니면 원래 문자?
                        // => 따로 저장공간을 마련해야 하는가? 그렇다. 왜냐하면, request 값과 비교를 해야 되는데 0과 1이 있으면 비교가 불가능하다.
                // 1. 외곽이라면 외곽의 범위를 변경한다.
                // 2. 근데, 1회 탐색이 아니라 빈 공간을 마주칠 수 있기 때문에 {}까지 계속 탐색을 한다.

                // 3. 외곽이 아니라면 단순 빈 공간으로 표시!
            
            int count = 0;
            List<Point> nextOutsideContainers = new ArrayList<>();
            
            if(request.length() == 2){   
                for(int i = 0 ; i < n; i++){
                    for(int j = 0; j < m; j++){
                        char container = storage[i].charAt(j);
                        
                        if(request.charAt(0) == container){
                            if(status[i][j] == 2){
                                status[i][j] = 1;
                                count++;
                            }
                        }
                    }
                }
            }

            for(int i = 0 ; i < n; i++){
                for(int j = 0; j < m; j++){
                    char container = storage[i].charAt(j);
                    
                    if(request.charAt(0) == container){
                        if(status[i][j] == 0){
                            status[i][j] = -1;
                            nextOutsideContainers.addAll(getBeingOutsideContainers(i, j));
                            count++;
                        }
                    }
                }
            }
            

            for(Point point: nextOutsideContainers){
                status[point.x][point.y] = 0;
            }

            return count;
        }

        boolean isOutside(int x, int y){
            return x == 0 || x == n - 1 || y == 0 || y == m - 1;
        }

        List<Point> getBeingOutsideContainers(int x, int y){
            List<Point> containers = new ArrayList<>();

            Queue<Point> queue = new ArrayDeque<>();
            queue.add(new Point(x, y));

            while(!queue.isEmpty()){
                Point point = queue.poll();

                for(int i = 0 ; i < 4; i++){
                    int nextX = point.x + dx[i];
                    int nextY = point.y + dy[i];
                    
                    if(nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                    if(status[nextX][nextY] == 0 || status[nextX][nextY] == -1)  continue;
                    if(status[nextX][nextY] == 1) queue.add(new Point(nextX, nextY));
                    else if(status[nextX][nextY] == 2) containers.add(new Point(nextX, nextY));
                }
            }
            return containers;
        }

        class Point{
            int x;
            int y;

            Point(int x, int y){
                this.x = x;
                this.y = y;
            }
        }
    }
}
