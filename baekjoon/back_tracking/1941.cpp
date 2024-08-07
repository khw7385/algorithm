//
// Created by hyunwon on 2024-08-05.
//
#include <iostream>
#include <queue>

using namespace std;

char arr[5][5];
bool visited[5][5];
int ans;

void input();
void solve();
void dfs(int idx, int std_num, int im_num);
bool is_connecting();
pair<int ,int> get_find_first_pos();

int main(){
    input();
    solve();
}

void input(){
    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            cin >> arr[i][j];
        }
    }
    fill_n(&visited[0][0], 5 * 5, false);
}

void solve(){
    ans = 0;

    dfs(0, 0, 0);

    cout << ans << endl;
}

void dfs(int idx, int std_num, int im_num){
    if(im_num >= 4){
        return;
    }

    if(std_num == 7){
        if(is_connecting()){
            ans++;
        }
        return;
    }

    for (int i = idx; i < 5 * 5; ++i) {
        int x = i / 5;
        int y = i % 5;

        if(visited[x][y]) continue;

        visited[x][y] = true;
        if(arr[x][y] == 'S') dfs(i + 1, std_num + 1, im_num);
        else dfs(i + 1, std_num + 1, im_num + 1);
        visited[x][y] = false;
    }
}

bool is_connecting(){
    bool counted[5][5];
    queue<pair<int, int>> q;
    int dx[4] = {-1, 0, 1, 0};
    int dy[4] = {0, 1, 0, -1};
    int cnt = 0;

    fill_n(&counted[0][0], 5 * 5, false);

    pair<int, int> start = get_find_first_pos();


    q.push(start);
    cnt++;
    counted[start.first][start.second] = true;

    while (!q.empty()) {
        pair<int, int> now = q.front(); q.pop();

        for (int i = 0; i < 4; ++i) {
            int next_x = now.first + dx[i];
            int next_y = now.second + dy[i];

            if(next_x < 0 || next_x >= 5 || next_y < 0 || next_y >= 5) continue;
            if (visited[next_x][next_y] && !counted[next_x][next_y]){
                cnt++;
                q.push({next_x, next_y});
                counted[next_x][next_y] = true;
            }
        }
    }

    if(cnt == 7) return true;
    return false;
}

pair<int, int> get_find_first_pos(){
    for (int i = 0; i < 5; ++i) {
        for (int j = 0; j < 5; ++j) {
            if(visited[i][j]) return {i, j};
        }
    }
}