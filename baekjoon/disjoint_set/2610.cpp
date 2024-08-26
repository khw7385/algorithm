//
// Created by hyunwon on 2024-08-23.
//
#include <iostream>
#include <vector>
#include <algorithm>
#include <map>

#define INF 987654321
#define N_MAX 101

using namespace std;

int n, m;
int committee_num;
int parent[N_MAX];
int dist[N_MAX][N_MAX];

void input();
int find(int x);
bool merge(int x, int y);
void solve();
void floydWarshall();

// 왜 안되는지 모르겠음. 21% 오류
int main(void){
    input();
    solve();
}
void input(){
    int per1, per2;

    cin >> n;
    cin >> m;

    committee_num = n;
    // 집합 자기 자신으로 초기화
    for (int i = 1; i <= n; ++i) {
        parent[i] = i;
    }

    for (int i = 1; i <= n ; ++i) {
        for (int j = 1; j <= n; ++j) {
            dist[i][j] = INF;
        }
    }

    for (int i = 1; i <= n ; ++i) {
        dist[i][i] = 0;
    }
    // 알고 있는 관계 입력 받은 후 분리 집합 형성
    for (int i = 0; i < m; ++i) {
        cin >> per1 >> per2;

        // 간선 표현
        dist[per1][per2] = dist[per2][per1] = 1;

        // 결합
        if(merge(per1, per2)){
            // 위원회 수 카운트
            committee_num--;
        }
    }
}
int find(int x){
    if(x == parent[x]) return x;
    else return parent[x] = find(parent[x]);
}
bool merge(int x, int y){
    int px = find(x);
    int py = find(y);

    if(px != py){
        if(px < py) parent[py] = parent[px];
        else parent[px] = parent[py];
        return true;
    }

    return false;
}

void floydWarshall(){
    // dp 를 기반으로 한 플로이드-와샬 알고리즘, 노드1, ... 노드 k 를 사용할 경우의 최소값은 노드1, .. , 노드 k-1 를 사용할 경우의 최소값과 노드 k 가 무조건 사용된 경우의 값 중 더 작은 값을 할당
    for (int k = 1; k <= n; ++k) {
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}

void solve(){
    // 결과 출력물을 위한 자료구조
    vector<int> representatives;

    // 각 위원회에 속하는 사람을 저장하기 위한 자료구조
    map<int, vector<int>> committee;
    // 위원회 구성
    for (int i = 1; i <= n; ++i) {
        committee[parent[i]].push_back(i); //키: 제일 작은 사람 인덱스, 값: 그 집합에 속한 사람들
    }

    floydWarshall();

    for (auto iter = committee.begin(); iter != committee.end() ; ++iter) {
        pair<int, int> represent_info = {iter-> second[0], INF}; // 사람 번호, 최대 값

        for (int j = 0; j < iter->second.size(); ++j) {
            int now_idx = iter->second[j]; // 기준, 대표
            int max_dist = 0;

            for (int k = 0; k < iter->second.size(); ++k) {
                if(iter -> second[k] == now_idx) continue;
                int next_idx = iter->second[k];
                max_dist = max(max_dist, dist[now_idx][next_idx]);
            }

            if(max_dist < represent_info.second){
                represent_info = {now_idx, max_dist};
            }
        }

        representatives.push_back(represent_info.first);
    }


    sort(representatives.begin(), representatives.end());
    // 위원회 수 출력
    cout << committee_num << endl;

    for (int i = 0; i < representatives.size(); ++i) {
        cout << representatives[i] << endl;
    }

}
