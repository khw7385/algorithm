//
// Created by hyunwon on 2024-03-13.
//
#include <iostream>

using namespace std;

int N, S;
int arr[100000];

int main(){
    int low_idx = 0, high_idx = 0;
    int sub_sum;
    int min_length;
    cin >> N >> S;

    for (int i = 0; i < N; ++i) {
        cin >> arr[i];
    }
    // 초기화
    min_length = N + 1;
    sub_sum = arr[low_idx];

    while(low_idx <= high_idx && high_idx < N) {
        if (sub_sum >= S) {
            min_length = min(min_length, high_idx - low_idx + 1);
            sub_sum -= arr[low_idx++];

        }
        else {
            sub_sum += arr[++high_idx];
        }
    }

    if(min_length == N + 1) min_length = 0;
    cout << min_length << endl;

    return 0;
}