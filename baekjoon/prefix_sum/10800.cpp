//
// Created by hyunwon on 2024-03-24.
//
#include <iostream>
#include <vector>
#include <algorithm>

#define MAX 200001

using namespace std;

struct ball{
    int idx;
    int color;
    int size;
};
int ans[MAX] = {0,};
int c[MAX] = {0, };
int s[MAX] = {0, };

bool comp(ball& a, ball& b);

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int N;
    vector<ball> balls;

    cin >> N;

    for (int i = 0; i < N; ++i) {
        int color, size;
        cin >> color >> size;
        balls.push_back({i, color, size});
    }

    sort(balls.begin(), balls.end(), comp);

    int sum = 0;

    for (int i = 0; i < N; ++i) {
        int idx = balls[i].idx;
        int color = balls[i].color;
        int size = balls[i].size;

        c[color] += size;
        s[size] += size;
        sum += size;

        ans[idx] = sum - c[color] - s[size] + size;
        if((i != 0) && (balls[i - 1].color == color) && (balls[i-1].size == size)) ans[idx] = ans[balls[i - 1].idx];
    }

    for (int i = 0; i < N; ++i) {
        cout << ans[i] << '\n';
    }
    return 0;
}

bool comp(ball &a, ball &b){
    if (a.size == b.size) return a.color < b.color;
    return a.size < b.size;
}