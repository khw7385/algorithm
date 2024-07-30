//
// Created by hyunwon on 2024-07-30.
//
#include <iostream>
#include <vector>

using namespace std;

struct material{
    int val = 1;
    vector<int> edges;
};

material arr[10];
int visit = 0;

int gcd(int a, int b);
void solve();
void update(int idx, int val);

int main(){
    solve();

    return 0;
}

int gcd(int a, int b){
    return b ? gcd(b, a % b) : a;
}

void solve(){
    int n;
    int a, b, p, q;
    int pq, a_val, b_val, ab;

    cin >> n;

    for (int i = 0; i < n - 1; ++i) {
        cin >> a >> b >> p >> q;
        pq = gcd(p, q);
        a_val = arr[b].val * p / pq;
        b_val = arr[a].val * q / pq;

        ab = gcd(a_val, b_val);
        visit = 0;

        update(a, a_val / ab);
        update(b, b_val / ab);

        arr[a].edges.push_back(b);
        arr[b].edges.push_back(a);
    }

    for (int i = 0; i < n; ++i) {
        cout << arr[i].val << ' ';
    }
}

void update(int idx, int value){
    arr[idx].val = arr[idx].val * value;
    visit = visit | 1 << idx;

    for(auto elem: arr[idx].edges){
        if((visit & (1 << elem)) == 0){
            update(elem, value);
        }
    }
}