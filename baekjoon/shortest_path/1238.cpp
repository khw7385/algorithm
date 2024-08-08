//
// Created by hyunwon on 2024-08-07.
//
#include <iostream>
#include <queue>
#include <vector>

#define N_MAX 1000
#define INF 10000000

using namespace std;

int n, m, x;
vector<pair<int, int>> edges[N_MAX];
int dist[N_MAX];

void input();
void solve();
int dijkstra(int start);

int main(){
    input();
    solve();
}

void input(){
    int from, to, cost;

    cin >> n >> m >> x;


    for (int i = 0; i < m; ++i) {
        cin >> from >> to >> cost;

        edges[from - 1].push_back({to - 1, cost});
    }
}

void solve(){
    int ans = 0;
    int min_dist[N_MAX];

    for (int i = 0; i < n; ++i) {
        if(i == x - 1) continue;
        min_dist[i] = dijkstra(i);
    }

    dijkstra(x - 1);
    min_dist[x - 1] = 0;
    for (int i = 0; i < n; ++i) {
        ans = max(ans, min_dist[i] + dist[i]);
    }

    cout << ans << endl;
}

int dijkstra(int start){
    priority_queue<pair<int, int>> pq;

    // 초기화
    fill_n(dist, n, INF);

    dist[start] = 0;
    pq.push({0, start});

    while (!pq.empty()) {
        pair<int, int> cur = pq.top();
        int cur_node = cur.second;
        int cur_dist = -cur.first;
        pq.pop();

        for (int i = 0; i < edges[cur_node].size(); ++i) {
            int next_node = edges[cur_node][i].first;
            int next_dist = cur_dist + edges[cur_node][i].second;

            if (dist[next_node] > next_dist) {
                dist[next_node] = next_dist;
                pq.push({-next_dist, next_node});
            }
        }
    }

    return dist[x - 1];
}