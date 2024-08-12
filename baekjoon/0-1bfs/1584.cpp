//
// Created by hyunwon on 2024-08-08.
//
#include <iostream>
#include <deque>

#define INF 2000

using namespace std;

enum sector{
    SAFE = 0, DANGER, DEATH
};

sector map[501][501];
int declined_life[501][501];

void input();
void solve();
void bfs();
int main(void){
    input();
    solve();

}

void input(){
    int n, m;
    int x1, y1, x2, y2;

    // 초기화
    fill_n(&map[0][0], 501 * 501, SAFE);

    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> x1 >> y1 >> x2 >> y2;

        for (int x = min(x1, x2); x <= max(x1, x2); ++x) {
            for (int y = min(y1, y2); y <= max(y1, y2); ++y) {
                map[x][y] = DANGER;
            }
        }
    }
    cin >> m;

    for (int i = 0; i < m; ++i) {
        cin >> x1 >> y1 >> x2 >> y2;
        for (int x = min(x1, x2); x <= max(x1, x2); ++x) {
            for (int y = min(y1, y2); y <= max(y1, y2); ++y) {
                map[x][y] = DEATH;
            }
        }
    }

    fill_n(&declined_life[0][0], 501 * 501, INF);
}

void solve(){
    bfs();
    if(declined_life[500][500] == INF){
        cout << -1 << endl;
    }else{
        cout << declined_life[500][500] << endl;
    }
}

void bfs(){
    int dx[4] = {-1, 0, 1, 0};
    int dy[4] = {0, 1, 0, -1};

    deque<pair<int, pair<int, int>>> dq;

    dq.push_back({0, {0, 0}});

    while(!dq.empty()){
        pair<int, pair<int ,int>> elem = dq.front(); dq.pop_front();

        int cost = elem.first;
        int cur_x = elem.second.first;
        int cur_y = elem.second.second;

        for (int i = 0; i < 4; ++i) {
            int next_x = cur_x + dx[i];
            int next_y = cur_y + dy[i];

            if(next_x < 0 || next_x > 500 || next_y < 0 || next_y > 500) continue;

            if(map[next_x][next_y] == DEATH) continue;
            else if(map[next_x][next_y] == DANGER){
                if(declined_life[next_x][next_y] > cost + 1){
                    declined_life[next_x][next_y] = cost + 1;
                    dq.push_back({cost + 1, {next_x, next_y}});
                }
            }else{
                if(declined_life[next_x][next_y] > cost){
                    declined_life[next_x][next_y] = cost;
                    dq.push_front({cost, {next_x, next_y}});
                }
            }
        }
    }
}