//
// Created by hyunwon on 2024-05-19.
//
#include <iostream>
#include <queue>

#define MAX 20

using namespace std;

int n;
int space[MAX][MAX];
int shark_size = 2;
int dx[4] = {0, -1, 1, 0};
int dy[4] = {-1, 0, 0, 1};
pair<int, int> shark_pos;
int eat_cnt = 0;
int result = 0;
bool stop = false;
bool eat;


void input();

void solve();

void bfs(bool visited[][MAX]);

int main(){
    input();
    solve();

    cout << result << endl;
    return 0;
}

void solve() {
    space[shark_pos.first][shark_pos.second] = 0;
    while(!stop){
        bool visited[MAX][MAX] = {0};
        bfs(visited);
        if(eat){
            space[shark_pos.first][shark_pos.second] = 0;
            if(eat_cnt == shark_size){
                shark_size += 1;
                eat_cnt = 0;
            }
        }else{
            stop = true;
        }
    }
}
// 1. bfs 를 이용하기 위해 큐에 담는다.
// 2. 큐에 담는 것들은 먹지는 못하지만 지나갈 수 있는 물고기를 담는다.
// 3. 위, 왼쪽 을 선으로 하기 위해서 방향에 대한 배열로 처리한다. -> 이것만으로 부족하다. 왼쪽과 오른쪽을 지나갈 수 있는 상황에서 왼쪽의 아래쪽을 먹올 수 있고 오른쪽의 위쪽이나 오른쪽을 먹을 수 있는 상황이라면 오른쪽의 위쪽을 먹는 것이 맞다. 왼쪽의
// 4.

void bfs(bool visited[][MAX]) {
    // <<x 위치, y 위치>, 시간>
    queue<pair<pair<int, int>, int>> q;
    q.push(make_pair(shark_pos,0));
    visited[shark_pos.first][shark_pos.second] = true;
    eat = false;
    int temp = 0;

    while(!q.empty()){
        // 위치
        int nx = q.front().first.second;
        int ny = q.front().first.first;
        // 시간
        int time = q.front().second;
        q.pop();

        if(space[ny][nx] > 0 && space[ny][nx] < shark_size && time == temp){
            if(ny < shark_pos.first || (ny == shark_pos.first) && (nx < shark_pos.second)){
                shark_pos.first = ny;
                shark_pos.second = nx;
                continue;
            }
        }

        for (int i = 0; i < 4; ++i) {
            int next_x = nx + dx[i];
            int next_y = ny + dy[i];

            if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < n && !visited[next_y][next_x]) {
                if (space[next_y][next_x] <= shark_size) { //지나가거나 먹을 수 있는 물고기
                    if (space[next_y][next_x] > 0 && space[next_y][next_x] < shark_size && !eat) {
                        // 위치 변동
                        shark_pos.first = next_y;
                        shark_pos.second = next_x;
                        eat_cnt += 1;
                        eat = true;
                        temp = (time + 1);
                        result += temp;
                    } else {
                        q.push(make_pair(make_pair(next_y, next_x), time + 1));
                    }
                    visited[next_y][next_x] = true;
                }
            }
        }

    }
}

void input() {
    int temp;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            cin >> temp;
            if(temp == 9){
                shark_pos = make_pair(i, j);
            }
            space[i][j] = temp;
        }
    }
}
