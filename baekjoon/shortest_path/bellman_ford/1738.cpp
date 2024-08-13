//
// Created by hyunwon on 2024-08-12.
//
#include <iostream>
#include <vector>

#define INF 1000000

using namespace std;

struct Edge{
    int from;
    int to;
    int cost;
};

int v, e;
vector<Edge> edges;
int before[101] = {0, };
int dist[101];

void input();
void solve();

int main(void){
    input();
    solve();
}

void input(){
    int from, to, cost;

    cin >> v >> e;

    for (int i = 0; i < e; ++i) {
        cin >> from >> to >> cost;
        edges.push_back({from, to, -cost});
    }

    fill_n(dist, 101, INF);
    dist[1] = 0;
}

void solve(){
    for (int i = 0; i <= v - 1; ++i) {
        for (int j = 0; j < e; ++j) {
            Edge edge = edges[j];
            if(dist[edge.from] == INF) continue;
            if(dist[edge.from] + edge.cost < dist[edge.to]){
                dist[edge.to] = dist[edge.from] + edge.cost;
                before[edge.to] = edge.from;
                if(i == v - 1) dist[edge.to] = -INF;
            }
        }
    }

    if(dist[v] == INF || dist[v] == -INF){
        cout << -1 << endl;
        return;
    }

    vector<int> path;
    int s = v;

    while(s != 0){
        path.push_back(s);
        s = before[s];
    }

    for (int i = path.size() - 1; i >=  0; --i) {
        cout << path[i] << ' ';
    }
}