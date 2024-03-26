//
// Created by hyunwon on 2024-03-26.
//
#include <iostream>
#include <vector>

#define MAX 987654321

using namespace std;

int N, M;
vector<pair<int, int>> house;
vector<pair<int, int>> chickens;
pair<int, int>not_close_chickens[14];
int ans = MAX;
int house_size = 0;
int chickens_size = 0;
int get_distance(pair<int, int> src, pair<int, int> dst){
    return abs(src.first - dst.first) + abs(src.second- dst.second);
}
void combination(int num, int idx);
int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> N >> M;

    for (int i = 1; i <= N ; ++i) {
        for (int j = 1; j <= N; ++j) {
            int input; cin >> input;
            if(input == 1) house.push_back({i, j});
            else if(input == 2) chickens.push_back({i, j});
        }
    }

    house_size = house.size();
    chickens_size = chickens.size();
    combination(0, 0);
    cout << ans << endl;
    return 0;
}

void combination(int num, int idx){
    if(num == M){
        int result = 0;
        for (int i = 0; i < house_size; ++i) {
            int distance = MAX;
            for (int j = 0; j < M; ++j) {
                 distance = min(distance, abs(house[i].first - not_close_chickens[j].first) + abs(house[i].second - not_close_chickens[j].second));
            }

            result += distance;
        }
        ans = min(ans, result);
        return;
    }
    else{
        for (int i = idx; i < chickens_size; ++i) {
            not_close_chickens[num] = chickens[i];
            combination(num + 1, i + 1);
        }
    }
}