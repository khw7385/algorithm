//
// Created by hyunwon on 2024-05-24.
//
#include <iostream>
#include <cmath>

#define N_MAX 16
#define INF 987654321

using namespace std;

int n;
pair<int, int> item[N_MAX];
double weight[N_MAX][N_MAX];
double dp[N_MAX][1 << N_MAX];

void input();
void solve();
void set_weight();
void init();
double dfs(int cur_pos, int status);
int main(){
    ios_base::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    input();
    solve();
    return 0;
}

void solve() {
    set_weight();
    init();
    double result = dfs(0, 1);
    printf("%f", result);
}

double dfs(int now, int status){
    if(status == (1 << n) -1){
        if(weight[now][0] == 0) return INF;
        return weight[now][0];
    }

    if (dp[now][status] != INF){
        return dp[now][status];
    }

    for (int next = 0; next < n; ++next) {
        if (status & (1 << next)) continue;
        dp[now][status] = min(dp[now][status], weight[now][next] + dfs(next, status | 1 << next));
    }

    return dp[now][status];
}

void init() {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < 1 << n; ++j) {
            dp[i][j] = INF;
        }
    }
}

void set_weight() {
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            if(i == j) continue;
            weight[i][j] = sqrt(pow((item[i].first - item[j].first), 2) + pow((item[i].second - item[j].second), 2));
        }
    }
}

void input() {
    cin >> n;
    int x, y;
    for (int i = 0; i < n; ++i) {
        cin >> x >> y;
        item[i] = make_pair(x, y);
    }
}
