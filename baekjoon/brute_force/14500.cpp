//
// Created by hyunwon on 2024-05-15.
//
#include <iostream>

#define MAX 501
#define MAX_VALUE 4000
using namespace std;

int paper[MAX][MAX];
int row, col;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};
int visited[MAX][MAX];

void input();

int dfs(int cnt, int x, int y);
int solve();


int last_tetromino(int x, int y);

int main(){
    input();
    int result = solve();

    cout << result << endl;

    return 0;
}

void input() {
    cin >> row >> col;

    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            cin >> paper[i][j];
        }
    }
}

int solve(){
    int max_result = 0;

    // 나머지 테크로미노
    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            visited[i][j] = 1;
            max_result = max(max_result, dfs(0, i, j));
            visited[i][j] = 0;
        }
    }

    // ㅗ 테크로메노
    for (int i = 0; i < row; ++i) {
        for (int j = 0; j < col; ++j) {
            max_result = max(max_result, last_tetromino(i, j));
        }
    }

    return max_result;
}


int last_tetromino(int x, int y) {
    // 안 되는 케이스
    if(x == 0 && y ==0 || x == row - 1 && y == 0 || x == 0 && y == col - 1 || x == row - 1 && y == row - 1) return 0;

    int sum = paper[x][y];
    int min_value = MAX_VALUE;

    for (int i = 0; i < 4; ++i) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        sum += paper[nx][ny];
        min_value = min(min_value, paper[nx][ny]);
    }

    return sum - min_value;
}

int dfs(int cnt, int x, int y) {
    if(cnt == 3) return paper[x][y];

    int max_value = 0;

    for (int i = 0; i < 4; ++i) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if(nx < 0 || nx >= row || ny < 0 || ny >= col) continue;

        if(!visited[nx][ny]){
            visited[nx][ny] = 1;
            max_value = max(max_value, dfs(cnt + 1, nx, ny));
            visited[nx][ny] = 0;
        }
    }

    return paper[x][y] + max_value;
}
