//
// Created by hyunwon on 2024-08-20.
//
#include <iostream>
#include <vector>
#include <algorithm>

#define N_MAX 10001

using namespace std;

int n, m;
vector<pair<pair<int ,int>, int>> edges;
int start, dest;
int parent[N_MAX];

int find(int x){
    if(parent[x] == x) return x;
    else return parent[x] = find(parent[x]);
}
void merge(int x, int y){
    int px = find(x);
    int py = find(y);

    if(px != py){
        if(px < py){
            parent[py] = px;
        }else{
            parent[px] = py;
        }
    }
}

void input();
void solve();

int main(void){
    input();
    solve();
}

void input(){
    int from, to, cost;

    cin >> n >> m;

    for (int i = 0; i < m; ++i) {
        cin >> from >> to >> cost;

        edges.push_back({{from, to}, cost});
    }

    cin >> start >> dest;

    for (int i = 1; i <= n; ++i) {
        parent[i] = i;
    }
}

void solve(){
    sort(edges.begin(), edges.end(), [](pair<pair<int, int>, int>& edge1, pair<pair<int, int>, int>& edge2)->bool{
        return edge2.second < edge1.second;
    });


    for(auto edge: edges){
        merge(edge.first.first, edge.first.second);

        if(find(start) == find(dest)){
            cout << edge.second << endl;
            return;
        }
    }
}
