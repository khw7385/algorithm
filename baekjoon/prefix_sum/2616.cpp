//
// Created by hyunwon on 2024-03-19.
//
#include <iostream>

using namespace std;

int main(){
    int N, K;
    int arr[50001] = {0, }; // 객차당 손님 수
    int sum_arr[50001] = {0, }; // 누적합을 위한 배열
    int dp[4][50001] ={0, };
    cin >> N;

    // 누적합을 구하는 과정
    for (int i = 1; i <= N; ++i) {
        cin >> arr[i];
        sum_arr[i] += sum_arr[i - 1] + arr[i];
    }

    cin >> K;

    // 동적계획법(bottom-up)
    for (int i = 1; i <= 3 ; ++i) {
        for (int j = i * K; j <= N; ++j) {
            dp[i][j] = max(dp[i][j-1], dp[i - 1][j - K] + sum_arr[j] - sum_arr[j - K]);
        }
    }

    cout << dp[3][N] << endl;
    return 0;
}