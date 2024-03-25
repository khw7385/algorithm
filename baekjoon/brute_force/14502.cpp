//
// Created by hyunwon on 2024-03-25.
//
#include <iostream>
#include <queue>
using namespace std;

void make_wall(int count);
int bfs();

void copy_arr(int src[8][8], int dst[8][8]);

int count_virus();

int N, M;
int arr[8][8];
int temp[8][8];
int dx[4] = {0, 1, 0 , -1};
int dy[4] = {-1, 0, 1, 0};
queue<pair<int, int>> q;
int ans = 0;

int main(void){

    cin >> N >> M;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            int input;
            cin >> input;
            arr[i][j] = input;
        }
    }

    make_wall(0);

    cout << ans << endl;
    return 0;
}

void make_wall(int count){
    if(count == 3){
        ans = max(ans, bfs());
        return;
    }
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if(arr[i][j] == 0){
                arr[i][j] = 1;
                make_wall(count + 1);
                arr[i][j] = 0;
            }
        }
    }
}

int bfs() {
    copy_arr(arr, temp);

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if(temp[i][j] == 2) q.push(make_pair(i, j));
        }
    }
    while(!q.empty()){
        pair<int, int> pos = q.front(); q.pop();

        for (int i = 0; i < 4; ++i) {
            int nx = pos.first + dx[i];
            int ny = pos.second + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if(temp[nx][ny] == 0){
                temp[nx][ny] = 2;
                q.push(make_pair(nx, ny));
            }
        }
    }

    return count_virus();
}

int count_virus() {
    int cnt = 0;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            if(temp[i][j] == 0) cnt++;
        }
    }

    return cnt;
}

void copy_arr(int src[8][8], int dst[8][8]) {
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            dst[i][j] = src[i][j];
        }
    }
}
