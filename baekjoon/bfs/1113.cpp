//
// Created by hyunwon on 2024-07-24.
//
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int n, m;
int max_height, min_height;
int arr[50][50];
int water[50][50];
bool visited[50][50];

int dx[4] = {-1,  0, 1, 0};
int dy[4] = {0 , 1, 0, -1};

void input();
void solve();
void bfs(int x, int y, int height);

int main(void){
    input();
    solve();
}

void input(){
    max_height = 0;
    min_height = 10;

    cin >> n >> m;

    char input;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> input;
            arr[i][j] = input - '0';

            max_height = max(max_height, input - '0');
            min_height = min(min_height, input - '0');
        }
    }

    for (int i = 0; i < n ; ++i) {
        for (int j = 0; j < m; ++j) {
            water[i][j] = max_height - arr[i][j];
        }
    }
}

void solve(){
    for (int h = max_height; h > min_height; --h) {

        fill_n(&visited[0][0], sizeof(visited), false);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {

                if(!visited[i][j] && water[i][j] > 0) {
                    for (int k = 0; k < 4; ++k) {
                        int next_x = i + dx[k];
                        int next_y = j + dy[k];


                        if(next_x < 0 || next_x == n  || next_y < 0 || next_y == m || arr[i][j] + water[i][j] > arr[next_x][next_y] + water[next_x][next_y]){
                            bfs(i, j, h);
                            break;
                        }
                    }
                }
            }
        }
    }

    int ans = 0;

    for (int i = 0; i < n ; ++i) {
        for (int j = 0; j < m; ++j) {
            ans += water[i][j];
        }
    }

    cout << ans << endl;
}

void bfs(int x, int y, int height){
    queue<pair<int, int>> q;

    q.push({x, y});
    visited[x][y] = true;
    while(!q.empty()){
        pair<int, int> cur_pos = q.front(); q.pop();

        int cur_x = cur_pos.first;
        int cur_y = cur_pos.second;

        water[cur_x][cur_y]--;

        for (int i = 0; i < 4; ++i) {
            int next_x = cur_x + dx[i];
            int next_y = cur_y + dy[i];

            if(visited[next_x][next_y]) continue;

            if(next_x < 0 || next_x == n  || next_y < 0 || next_y == m) continue;

            if(arr[next_x][next_y] + water[next_x][next_y] == height && water[next_x][next_y] > 0){
                q.push({next_x, next_y});
                visited[next_x][next_y] = true;
            }
        }

    }
}
