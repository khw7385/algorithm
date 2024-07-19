#include <iostream>
#include <vector>

#define VERTEX_MAX 100001

using namespace std;

int vertex_num;
vector<pair<int, int>> edges[VERTEX_MAX];
bool visited[VERTEX_MAX];
int selected;
int max_dist;

void input();
void solve();
void dfs(int now, int dist);

int main(void){
    input();
    solve();
}

void input(){
    int start, dest, dist;

    cin >> vertex_num;

    for (int i = 0; i < vertex_num; ++i) {
        cin >> start;

        while(true){
            cin >> dest;

            if(dest == -1) {
                break;
            }
            cin >> dist;

            edges[start].push_back(make_pair(dest, dist));
        }
    }

}
void solve(){
    max_dist = 0;
    dfs(1, 0);

    fill_n(visited, vertex_num + 1, false);
    max_dist = 0;

    dfs(selected, 0);

    cout << max_dist << endl;
}
void dfs(int now, int dist){
    if(visited[now]){
        return;
    }

    if(dist > max_dist){
        max_dist = dist;
        selected = now;
    }
    visited[now] = true;

    for (int i = 0; i < edges[now].size(); ++i) {
        int next = edges[now][i].first;
        int cost = edges[now][i].second;

        dfs(next, dist + cost);
    }
}