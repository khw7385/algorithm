//
// Created by hyunwon on 2024-03-30.
//
#include <iostream>

#define MAX 987654321

using namespace std;

int ladders[31][11];
int N, M, H;
int ans = MAX;

void dfs(int row_idx, int col_idx, int num, int max_num);

bool is_success();

int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int a, b;
    cin >> N >> M >> H;

    for (int i = 0; i < M; ++i) {
        cin >> a >> b;
        ladders[a][b] = 1;
        ladders[a][b + 1] = 2;
    }
    for (int i = 0; i <= 3; ++i) { //최대 3번까지 놓아 본다.
        dfs(1, 1, 0, i);
        if(ans != MAX) break;
    }

    if (ans == MAX) cout << -1 << endl;
    else cout << ans << endl;

    return 0;
}

void dfs(int row_idx, int col_idx, int num, int max_num) {
    if (num == max_num) {
        if(is_success()){
            ans = num;
            return;
        }
    }
    else {
        for (int j = col_idx; j < N; j++, row_idx=1) {
            for (int i = row_idx; i <= H; ++i) {
                if(ladders[i][j] || ladders[i][j + 1]) continue;

                // 놓을 필요가 없음 , 1이면 오른쪽으로 가로선이 존재, 2이면 왼쪽으로 가로선이 존재
                ladders[i][j] = 1;
                ladders[i][j + 1] = 2;
                dfs(i, j, num + 1, max_num);
                ladders[i][j] = 0;
                ladders[i][j + 1] = 0;

                while(ladders[i + 1][j] || ladders[i + 1][j + 1]) i++;
            }
        }
    }
    return;
}

bool is_success() {
    for (int i = 1; i <= N; ++i) {
        int now_row = 1;
        int now_col = i;

        while(now_row <= H){
            if(ladders[now_row][now_col] == 1) now_col = now_col + 1;
            else if(ladders[now_row][now_col] == 2) now_col = now_col - 1;
            now_row++;
        }

        if(i != now_col) return false;
    }
    return true;
}
