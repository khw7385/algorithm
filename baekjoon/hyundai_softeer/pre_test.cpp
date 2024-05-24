//
// Created by hyunwon on 2024-05-23.
//
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

#define MAX 10

int arr[MAX][MAX];
int visited[MAX][MAX];
vector<int> result;
int n;

void input();
void solve();
int bfs(int y, int x);

void output();

int dx[4] = {1, 0, -1 , 0};
int dy[4] = {0, 1, 0, -1};

int main() {
    input();
    solve();
    output();
    return 0;
}

void output() {
    sort(result.begin(), result.end());
    cout << result.size() << endl;
    for_each(result.begin(), result.end(), [](int elem){
        cout << elem << " ";
    });
}

void input() {
    cin >> n;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> arr[i][j];
        }
    }
}

void solve(){
    int size;

    for(int i = 0 ; i < n; i++){
        for(int j = 0; j < n; j++){
            if(arr[i][j] == 1 && visited[i][j] == 0){
                size = bfs(i, j);
                result.push_back(size);
            }
        }
    }

}

int bfs(int y, int x){
    queue<pair<int, int>> q;
    q.push(make_pair(x, y));
    int size = 0;
    int now_x;
    int now_y;

    visited[y][x] = 1;
    while(!q.empty()){
        pair<int, int> pos = q.front();

        now_x = pos.first;
        now_y = pos.second;

        size++;

        q.pop();

        for (int i = 0; i < 4; ++i) {
            int next_x = now_x + dx[i];
            int next_y = now_y + dy[i];

            if(arr[next_y][next_x] == 1 && visited[next_y][next_x] == 0){
                q.push(make_pair(next_x, next_y));
                visited[next_y][next_x] = 1;
            }
        }
    }

    return size;
}