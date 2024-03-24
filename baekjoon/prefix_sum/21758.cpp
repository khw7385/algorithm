//
// Created by hyunwon on 2024-03-20.
//
#include <iostream>

using namespace std;

int main(void){
    int N;
    int arr[100001] = {0, };
    long long int sum_arr[100001] = {0, };
    cin >> N;


    for (int i = 1; i <= N; ++i) {
        cin >> arr[i];
        sum_arr[i] = sum_arr[i-1] + arr[i];
    }
    long long int ans = 0;

    for (int i = 1; i <= N; ++i) {
        if(i != 1 && i != N) {
            ans = max(ans, sum_arr[N] + arr[i] - arr[1] - arr[N]);
        }
        else if (i == 1){
            for (int j = 2; j < N; ++j) {
                ans = max(ans, sum_arr[N - 1] - arr[j] + sum_arr[j - 1]);
            }
        }else{
            for (int j = 2; j < N; ++j) {
                ans = max(ans, sum_arr[N] - arr[1] - arr[j] + sum_arr[N] - sum_arr[j]);
            }
        }
    }

    cout << ans << endl;

    return 0;
}