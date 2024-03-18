//
// Created by hyunwon on 2024-03-18.
//
#include <iostream>

#define MAX 1000001
using namespace std;

long long int arr[MAX];

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int test;
    cin >> test;


    for(int i = 1; i < MAX; i++){
        for (int j = i; j < MAX; j += i) {
            arr[j] += i;
        }
        arr[i] += arr[i - 1];
    }

    for (int i = 0; i < test; ++i) {
        int N; cin >> N;

        cout << arr[N] << '\n';
    }
    return 0;
}
