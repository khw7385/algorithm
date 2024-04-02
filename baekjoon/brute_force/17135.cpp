//
// Created by hyunwon on 2024-03-31.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int grid[16][16];

vector<int> archers;
int ans = 0;
int N, M, D;
int total_enemies = 0;
void dfs(int idx, int num);

int game_start();

void copy_grid(int (*dst)[16], int (*src)[16]);

int main(void){
    cin >> N >> M >> D;

    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            int input; cin >> input;
            if(input == 1) total_enemies++; // 적 증가
            grid[i][j] = input;
        }
    }

    dfs(1, 0);

    cout << ans << endl;

    return 0;
}

void dfs(int idx, int num) {
    if(num == 3){
        ans = max(ans, game_start());
    }else{
        for (int i = idx; i <= M; ++i) {
            archers.push_back(i);
            dfs(i + 1, num + 1);
            archers.pop_back();
        }
    }
}

int game_start() {
    int copy[16][16];
    int excluded_enemies = 0; // 없어진 적
    int killed_enemies = 0; // 궁수에게 죽임을 당한 적
    int bound = 0;

    copy_grid(copy, grid);

    while(excluded_enemies != total_enemies){
        vector<pair<int, int>> round_attack_enemies;
        int round_attack_enemies_size = 0;

        for (int i = 0; i < 3; ++i) {
            int archer_x_pos = archers[i];
            int archer_y_pos = N + 1;
            int find_flag = false;

            for (int distance = 1; distance <= D; ++distance) {
                for (int sum = 1 - distance; sum <= distance - 1; ++sum) {
                    int attack_x_pos = archer_x_pos + sum;
                    int attack_y_pos = archer_y_pos -(distance - abs(sum));

                    if(attack_x_pos <= 0 || attack_x_pos > M || attack_y_pos <= bound) continue;

                    if(copy[attack_y_pos][attack_x_pos]){
                        int dead_flag = false; // 이미 제거한 적인지 확인

                        for (int j = 0; j < round_attack_enemies_size; ++j) {
                            if (round_attack_enemies[j].first == attack_y_pos && round_attack_enemies[j].second == attack_x_pos){
                                dead_flag = true;
                                break;
                            }
                        }
                        if(!dead_flag) {
                            round_attack_enemies.push_back(make_pair(attack_y_pos, attack_x_pos));
                            round_attack_enemies_size++;
                        }
                        find_flag = true;
                        break;
                    }
                }
                if(find_flag) break;
            }
        }

        // 공격 끝
        excluded_enemies += round_attack_enemies_size;
        killed_enemies += round_attack_enemies_size;

        while(round_attack_enemies_size--){
            auto enemy_pos = round_attack_enemies.back();
            copy[enemy_pos.first][enemy_pos.second] = 0;
            round_attack_enemies.pop_back();
        }



        for (int i = 1; i <= M; ++i) {
            if(copy[N][i]) excluded_enemies++;
        }

        for (int i = N - 1; i > bound ; --i) {
            for (int j = 1; j <= M; ++j) {
                copy[i + 1][j] = copy[i][j];
            }
        }
        bound++;

    }

    return killed_enemies;
}

void copy_grid(int (*dst)[16], int (*src)[16]) {
    for (int i = 1; i <= N; ++i) {
        for (int j = 1; j <= M; ++j) {
            dst[i][j] = src[i][j];
        }
    }
}
