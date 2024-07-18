//
// Created by hyunwon on 2024-07-17.
//
#include <iostream>
#include <vector>
#include <cmath>

#define INF 3000000

using namespace std;

int n;
pair<int, int> points[20];
bool visited[20];
vector<pair<int, int>> vectors;
double result;

void input();

void dfs(int now, int num);
double vec_sum();
int main(){
    int test;

    cin >> test;

    for (int i = 0; i < test; ++i) {
        result = 1e9+7;
        input();

        dfs(0, 0);

        printf("%.7lf\n", result);
    }
}

void input(){
    cin >> n;
    int x, y;
    for (int i = 0; i < n; ++i) {
        cin >> x >> y;
        points[i] = make_pair(x, y);
    }
}

void dfs(int now, int num){
    // 종료 조건
    if(num == n/2){
        result = min(result, vec_sum());
        return;
    }

    for (int i = now; i < n; ++i) {
        visited[i] = true;
        dfs(i + 1, num + 1);
        visited[i] = false;
    }
}
double vec_sum(){
    int x = 0, y = 0;
    for (int i = 0; i < n; ++i) {
        if(visited[i]){
            x += points[i].first;
            y += points[i].second;
        }
        else{
            x -= points[i].first;
            y -= points[i].second;
        }
    }

    return sqrt(pow(x, 2) + pow(y, 2));
}
