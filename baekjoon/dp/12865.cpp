//
// Created by hyunwon on 2024-05-24.
//
#include <iostream>

#define ITEM_MAX 101
#define WEIGHT_MAX 100001

using namespace std;

int n;
int max_weight;
pair<int, int> item[ITEM_MAX];
int dp[ITEM_MAX][WEIGHT_MAX];

void input();

void solve();

void output();

int main(){
    input();
    solve();
    output();
    return 0;
}

void output() {
    cout << dp[n][max_weight] << endl;
}

void solve() {
    for (int i = 1; i <= n; ++i) {
        int weight = item[i].first;
        int value = item[i].second;

        for(int j = 1 ; j <= max_weight; j++){ // 계속 담는 걸 못하게 한다.
            if(weight <= j){ //
                dp[i][j] = max(dp[i - 1][j], value + dp[i - 1][j - weight]); // j
            }else{
                dp[i][j] = dp[i - 1][j];
            }
        }
    }
}

void input() {
    cin >> n >> max_weight;
    int weight, value;
    for (int i = 1; i <= n; ++i) {
        cin >> weight >> value;

        item[i] = make_pair(weight, value);
    }
}
