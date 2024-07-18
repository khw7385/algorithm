//
// Created by hyunwon on 2024-04-02.
//
#include <iostream>
#include <vector>
#include <queue>


using namespace std;

struct Edge{
    int start_island;
    int end_island;
    int dist;

    Edge(int startIsland, int endIsland, int dist) : start_island(startIsland), end_island(endIsland), dist(dist) {}
};

void get_islands();

void find_island();

void bfs(int x, int y, int label);


void find_edges();

void check_edge(int start_id, int x, int y, int dir);

int N, M;
int label = 1;
int map[11][11];
int new_map[11][11];
int visited[11][11];
int bridges[7][7];

vector<pair<int, int>> islands;
vector<Edge> edges;

int dx[4] = {-1, 0, 1 ,0 };
int dy[4] = { 0, 1, 0, -1};

int main(void){
    cin >> N >> M;

    for (int i = 1; i <= N ; ++i) {
        for (int j = 1; j <= M; ++j) {
            cin >> map[i][j];

            // 섬의 좌표를 저장
            if(map[i][j] != 0){
                islands.push_back(make_pair(i, j));
            }
        }
    }

    find_island(); // 섬 찾기
    find_edges();

    return 0;
}

void find_edges() {
    for (int i = 0; i < islands.size(); ++i) {
        int x = islands[i].first;
        int y = islands[i].second;

        for (int dir = 0; dir < 4; ++dir) {
            int next_x = x + dx[dir];
            int next_y = y + dy[dir];
            if(next_x > 0 && next_x <= N && next_y > 0 && next_y <= M) {
                if (new_map[next_x][next_y] == 0) {
                    check_edge(new_map[next_x][next_y], next_x, next_y, dir);
                }
            }
        }

    }
}

void check_edge(int start_id, int x, int y, int dir) {
    int next_x = x;
    int next_y = y;
    int dist = 1;

    while (1) {
        next_x = next_x + dx[dir];
        next_y = next_y + dy[dir];

        if(next_x > 0 && next_x <= N && next_y > 0 && next_y <= M) {
            if (new_map[next_x][next_y] == 0) {
                dist++;
            }
            else{
                if (dist >= 2) {
                    edges.push_back(Edge(start_id, new_map[next_x][next_y], dist));
                }
                break;
            }
        }else{
            break;
        }
    }
}

void find_island() {
    for (int i = 0; i < islands.size(); ++i) {
        int x = islands[i].first;
        int y = islands[i].second;

        if (!visited[x][y]) {

            bfs(x, y, label);
            label++;
        }
    }
}

void bfs(int x, int y, int label) {
    queue<pair<int, int>> queue;
    queue.push(make_pair(x, y));

    new_map[x][y] = label;
    visited[x][y] = 1;

    while(!queue.empty()) {
        auto pos = queue.back();queue.pop();
        int now_x = pos.first;
        int now_y = pos.second;

        for (int i = 0; i < 4; ++i) {
            int next_x = now_x + dx[i];
            int next_y = now_y + dy[i];

            if (map[next_x][next_y] && !visited[next_x][next_y]) {
                visited[next_x][next_y] = 1;
                new_map[next_x][next_y] = label;
                queue.push(make_pair(next_x, next_y));
            }
        }
    }

}


