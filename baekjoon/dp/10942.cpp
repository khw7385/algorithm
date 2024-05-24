//
// Created by hyunwon on 2024-05-24.
//
#include <iostream>

#define N_MAX 2000
#define QUESTION_MAX 1000000
using namespace std;

int n, m;
int arr[N_MAX];
pair<int, int> question[QUESTION_MAX];
int palindrome[N_MAX][N_MAX];

void input();

void solve();

int main(){
    ios_base::sync_with_stdio(false); cin.tie(0); cout.tie(0);
    input();
    solve();
    return 0;
}

void solve() {

    for (int i = 0; i < n; ++i) {
        palindrome[i][i] = 1;
    }

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            palindrome[i][j] = 1;
        }
    }
    for (int i = n - 1; i >= 0; --i) {
        for (int j = n - 1; j > i; --j) {
           palindrome[i][j] = (arr[i] == arr[j]) && (palindrome[i + 1][j - 1]);
        }
    }

    for (int i = 0; i < m; ++i) {
        int from = question[i].first;
        int to = question[i].second;

        cout << palindrome[from][to] << '\n';
    }   
}

void input() {
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> arr[i];
    }
    cin >> m;
    int first;
    int last;

    for (int i = 0; i < m; ++i) {
        cin >> first >> last;
        question[i] = make_pair(first - 1, last - 1);
    }
}
