//
// Created by hyunwon on 2024-05-21.
//
#include <iostream>

#define MAX 1000

using namespace std;

int test_case;
int n;
int preorder[MAX];
int inorder[MAX];

void input();

void solve();

void post_order(int start_pos, int end_pos, int pos);

int main(){
    cin >> test_case;

    for (int i = 0; i < test_case; ++i) {
        input();
        solve();
    }
}

void solve() {
    int root_idx = 0;
    post_order(root_idx, n - 1, 0);
    cout << endl;
}

void post_order(int start_pos, int end_pos, int pos) {
    for (int i = start_pos; i <= end_pos; ++i) {
        if(preorder[pos] == inorder[i]){
            post_order(start_pos, i - 1, pos + 1);
            post_order(i + 1, end_pos, pos + i - start_pos + 1);
            cout << inorder[i] << ' ';
        }
    }
}

void input() {
    cin >> n;

    for (int i = 0; i < n; ++i) {
        cin >> preorder[i];
    }

    for (int i = 0; i < n; ++i) {
        cin >> inorder[i];
    }
}
