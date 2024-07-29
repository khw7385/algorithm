//
// Created by hyunwon on 2024-07-29.
//
#include <iostream>
#include <queue>

#define MAX_SIZE 50

using namespace std;

enum Direction{
    UP = 0, RIGHT, DOWN, LEFT
};

struct Deliver{
    int x_pos;
    int y_pos;
    int status;
    Direction dir;
};

int n, m;
int start_x, start_y;
Direction start_dir;
int cx = -1, cy = -1;

char classroom[MAX_SIZE][MAX_SIZE];
int dp[MAX_SIZE][MAX_SIZE][4][1 << 2];

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

void input();
void solve();
int bfs();

int main(void){
    input();
    solve();
}

void input(){
    cin >> n >> m;
    char temp;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> temp;

            if(temp == 'S'){
                start_x = i;
                start_y = j;
                temp = '.';
            }
            else if(temp == 'C' && cy == -1){
                cx = i; cy= j;
            }
            classroom[i][j] = temp;
        }
    }
    fill_n(&dp[0][0][0][0], MAX_SIZE * MAX_SIZE * 4 * 4, -1);

    for (int i = 0; i < 4; ++i) {
        dp[start_x][start_y][i][0] = 0;
    }
}

void solve(){
    int result;
    queue<Deliver> q;
    bool resolved = false;
    q.push({start_x, start_y, 0, UP});
    q.push({start_x, start_y, 0 , RIGHT});
    q.push({start_x, start_y, 0, DOWN});
    q.push({start_x, start_y, 0, LEFT});

    while(!q.empty()){
        Deliver item = q.front();q.pop();

        int now_x = item.x_pos;
        int now_y = item.y_pos;
        int status = item.status;
        Direction dir= item.dir;

        for (int i = 0; i < 4; ++i) {
            if(i == dir) continue; // 같은 방향이면 pass

            int next_x = now_x + dx[i];
            int next_y = now_y + dy[i];

            if(next_x < 0 || next_x >= n || next_y < 0 || next_y >= m) continue;

            if (classroom[next_x][next_y] == '#') continue;

            if(dp[next_x][next_y][i][status] == -1){
                int next_status = status;

                if(classroom[next_x][next_y] == 'C'){
                    if(next_x == cx && next_y == cy) next_status = status | 1;
                    else {
                        next_status = status | 2;
                    }
                    if(next_status == 3){
                        resolved = true;
                        result = dp[now_x][now_y][dir][status] + 1;
                    }
                }
                dp[next_x][next_y][i][next_status] = dp[now_x][now_y][dir][status] + 1;
                q.push({next_x, next_y, next_status, static_cast<Direction>(i)});
                if(resolved) break;
            }
        }

        if(resolved) break;
    }

    if(!resolved){
        cout << -1 << endl;
    } else{
        cout << result << endl;
    }
}
