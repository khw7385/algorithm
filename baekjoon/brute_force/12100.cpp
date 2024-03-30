//
// Created by hyunwon on 2024-03-27.
//
#include <iostream>
#include <cmath>
using namespace std;

int N;
int board[6][21][21];
int ans = 0;

void dfs(int num);

int main(void){
    cin >> N;

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            cin >> board[0][i][j];
        }
    }

    dfs(0);

    cout << ans << endl;
    return 0;
}


void dfs(int num){
    if(num == 5){
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                ans = max(ans, board[num][i][j]);
            }
        }
    }else{
        int max_tmp = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                max_tmp = max(max_tmp, board[num][i][j]);
            }
        }
        ans = max(ans, max_tmp);
        // 백트래킹 (현재 상태에 따라 5번을 진행했을 때 블록의 최댓값은 정해져 있다.)
        if(max_tmp * pow(2, 5 - num) <= ans){
            return;
        }
        // 오른쪽 이동
        for (int i = 0; i < N ; ++i) {
            int temp_zero = N - 1;
            int temp_not_zero_origin = N - 1;
            int temp_not_zero_change = N - 1;
            
            if (board[num][i][N - 1] == 0) {
                temp_not_zero_origin = N - 2;
                temp_not_zero_change = N - 2;
            }
            else temp_zero = N - 2;
            board[num + 1][i][N - 1] = board[num][i][N - 1];

            for (int j = N - 2; j >= 0; --j) {
                if (board[num][i][j] == 0) {
                    // 앞의 값이 계속 0 였다면
                    if (temp_not_zero_change == j) {
                        temp_not_zero_origin = j - 1;
                        temp_not_zero_change = j - 1;
                    }
                    board[num + 1][i][j] = 0;
                } else {
                    if (temp_not_zero_change > j) {
                        if (board[num][i][j] == board[num][i][temp_not_zero_origin] && board[num][i][j] == board[num + 1][i][temp_not_zero_change]) {
                            board[num + 1][i][temp_not_zero_change] = board[num][i][j] + board[num + 1][i][temp_not_zero_change];
                            board[num + 1][i][j] = 0;
                            temp_not_zero_origin = j;
                        } else {
                            board[num + 1][i][temp_zero] = board[num][i][j];
                            if(temp_zero != j) board[num + 1][i][j] = 0;
                            temp_not_zero_origin = j;
                            temp_not_zero_change = temp_not_zero_change - 1;
                            temp_zero = temp_zero - 1;
                        }
                    } else {
                        board[num + 1][i][temp_zero] = board[num][i][j];
                        board[num + 1][i][j] = 0;
                        temp_not_zero_origin = j;
                        temp_not_zero_change = temp_zero;
                        temp_zero = temp_zero - 1;
                    }
                }
            }
        }
        dfs(num + 1);
        // 왼쪽 이동
        for (int i = 0; i < N ; ++i){
            int temp_zero = 0;
            int temp_not_zero_origin = 0;
            int temp_not_zero_change = 0;

            if (board[num][i][0] == 0) temp_not_zero_change = 1;
            else temp_zero = 1;
            board[num + 1][i][0] = board[num][i][0];

            for (int j = 1; j < N ; ++j) {
                if(board[num][i][j] == 0){
                    // 앞의 값이 계속 0 였다면
                    if(temp_not_zero_change == j) {
                        temp_not_zero_origin = j + 1;
                        temp_not_zero_change = j + 1;
                    }board[num + 1][i][j] = 0;
                }else{
                    if(temp_not_zero_change < j) {
                        if(board[num][i][j] == board[num][i][temp_not_zero_origin] && board[num][i][j] == board[num + 1][i][temp_not_zero_change]){
                            board[num + 1][i][temp_not_zero_change] = board[num][i][j] + board[num + 1][i][temp_not_zero_change];
                            board[num + 1][i][j] = 0;
                            temp_not_zero_origin = j;
                        }
                        else {
                            board[num + 1][i][temp_zero] = board[num][i][j];
                            if(temp_zero != j) board[num + 1][i][j] = 0;
                            temp_not_zero_origin = j;
                            temp_not_zero_change = temp_not_zero_change + 1;
                            temp_zero = temp_zero + 1;
                        }
                    }else{
                        board[num + 1][i][temp_zero] = board[num][i][j];
                        board[num + 1][i][j] = 0;
                        temp_not_zero_origin = j;
                        temp_not_zero_change = temp_zero;
                        temp_zero = temp_zero + 1;
                    }

                }
            }
        }
        dfs(num + 1);
        // 위쪽 이동
        for (int i = 0; i < N ; ++i){
            int temp_zero = 0;
            int temp_not_zero_origin = 0;
            int temp_not_zero_change = 0;

            if (board[num][0][i] == 0){
                temp_not_zero_origin = 1;
                temp_not_zero_change = 1;
            }
            else temp_zero = 1;
            board[num + 1][0][i] = board[num][0][i];

            for (int j = 1; j < N ; ++j) {
                if(board[num][j][i] == 0){
                    // 앞의 값이 계속 0 였다면
                    if(temp_not_zero_change == j) {
                        temp_not_zero_origin = j + 1;
                        temp_not_zero_change = j + 1;
                    }
                    board[num + 1][j][i] = 0;
                }else{
                    if(temp_not_zero_change < j) {
                        if(board[num][j][i] == board[num][temp_not_zero_origin][i] && board[num][j][i] == board[num + 1][temp_not_zero_change][i]){
                            board[num + 1][temp_not_zero_change][i] = board[num][j][i] + board[num + 1][temp_not_zero_change][i];
                            board[num + 1][j][i] = 0;
                            temp_not_zero_origin = j;
                        }
                        else {
                            board[num + 1][temp_zero][i] = board[num][j][i];
                            if(temp_zero != j) board[num + 1][j][i] = 0;
                            temp_not_zero_origin = j;
                            temp_not_zero_change = temp_not_zero_change + 1;
                            temp_zero = temp_zero + 1;
                        }
                    }else{
                        board[num + 1][temp_zero][i] = board[num][j][i];
                        board[num + 1][j][i] = 0;
                        temp_not_zero_origin = j;
                        temp_not_zero_change = temp_zero;
                        temp_zero = temp_zero + 1;
                    }
                }
            }
        }
        dfs(num + 1);
        // 아래쪽 이동
        for (int i = 0; i < N ; ++i){
            int temp_zero = N - 1;
            int temp_not_zero_origin = N - 1;
            int temp_not_zero_change = N - 1;

            if (board[num][N - 1][i] == 0) {
                temp_not_zero_origin = N - 2;
                temp_not_zero_change = N - 2;
            }else temp_zero = N - 2;
            board[num + 1][N - 1][i] = board[num][N - 1][i];

            for (int j = N - 2; j >= 0; --j) {
                if(board[num][j][i] == 0){
                    // 앞의 값이 계속 0 였다면
                    if(temp_not_zero_change == j) {
                        temp_not_zero_origin = j - 1;
                        temp_not_zero_change = j - 1;
                    }board[num + 1][j][i] = 0;
                }else{
                    if(temp_not_zero_change > j) {
                        if(board[num][j][i] == board[num][temp_not_zero_origin][i] && board[num][j][i] == board[num + 1][temp_not_zero_change][i]) {
                            board[num + 1][temp_not_zero_change][i] = board[num][j][i] + board[num + 1][temp_not_zero_change][i];
                            board[num + 1][j][i] = 0;
                            temp_not_zero_origin = j;
                        }
                        else {
                            board[num + 1][temp_zero][i] = board[num][j][i];
                            if(temp_zero != j) board[num + 1][j][i] = 0;
                            temp_not_zero_origin = j;
                            temp_not_zero_change = temp_not_zero_change - 1;
                            temp_zero = temp_zero - 1;
                        }
                    }else{
                        board[num + 1][temp_zero][i] = board[num][j][i];
                        board[num + 1][j][i] = 0;
                        temp_not_zero_origin = j;
                        temp_not_zero_change = temp_zero;
                        temp_zero = temp_zero - 1;
                    }
                }
            }
        }
        dfs(num + 1);
    }
}