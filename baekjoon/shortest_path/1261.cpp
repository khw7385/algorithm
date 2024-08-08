//
// Created by hyunwon on 2024-08-08.
//
#include <iostream>
#include <queue>

#define N_M_MAX 100
#define INF 200
using namespace std;

int m, n;
int graph[N_M_MAX][N_M_MAX];
int dist[N_M_MAX][N_M_MAX];

void input();
void solve();
void dijkstra();

int main(void){
    input();
    solve();
    return 0;
}

void input(){
    char input;

    cin >> m >> n;

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            cin >> input;
            graph[i][j] = input - '0';
        }
    }

    fill_n(&dist[0][0], N_M_MAX * N_M_MAX, INF);
}

void solve(){
    int ans;

    dijkstra();

    ans = dist[n - 1][m - 1];

    cout << ans << endl;

}

void dijkstra(){
    priority_queue<pair<int, pair<int, int>>> pq;
    int dx[4] = {-1, 0, 1, 0};
    int dy[4] = {0, 1, 0, -1};

    pq.push({0, {0, 0}});
    dist[0][0] = 0;

    while(!pq.empty()) {
        pair<int, pair<int ,int>> elem = pq.top(); pq.pop();
        int cost = -elem.first;
        int cur_x = elem.second.first;
        int cur_y = elem.second.second;

        for (int i = 0; i < 4; ++i) {
            int next_x = cur_x + dx[i];
            int next_y = cur_y + dy[i];

            if(next_x < 0 || next_x >= n || next_y < 0 || next_y >= m) continue;

            if(dist[next_x][next_y] > cost + graph[next_x][next_y]){
                dist[next_x][next_y] = cost + graph[next_x][next_y];
                pq.push({-(cost + graph[next_x][next_y]), {next_x, next_y}});
            }

        }
    }

}