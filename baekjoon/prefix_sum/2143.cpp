//
// Created by hyunwon on 2024-03-17.
//
//
// Created by hyunwon on 2024-03-16.
//
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int A[1001];
int B[1001];
vector<int> A_SUM;
vector<int> B_SUM;
int main(){
    long int T;
    int n, m;

    cin >> T;

    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> A[i];
    }

    cin >> m;
    for (int i = 0; i < m; ++i) {
        cin >> B[i];
    }

    // A, B 배열의 누적 합 구하기
    for (int i = 0; i < n; ++i) {
        int sum = A[i];
        A_SUM.push_back(sum);
        for (int j = i + 1; j < n; ++j) {
            sum += A[j];
            A_SUM.push_back(sum);
        }
    }

    for (int i = 0; i < m; ++i) {
        int sum = B[i];
        B_SUM.push_back(sum);
        for (int j = i + 1; j < m; ++j) {
            sum += B[j];
            B_SUM.push_back(sum);
        }
    }

    sort(A_SUM.begin(), A_SUM.end());
    sort(B_SUM.begin(), B_SUM.end());

    long int sum = 0;
    for (int i = 0; i < A_SUM.size(); ++i) {
        long int rest = T - A_SUM[i];

        int first_idx = lower_bound(B_SUM.begin(), B_SUM.end(), rest) - B_SUM.begin();
        int last_idx = upper_bound(B_SUM.begin(), B_SUM.end(), rest) - B_SUM.begin();

        sum += last_idx - first_idx;
    }

    cout << sum << endl;

    return 0;
}