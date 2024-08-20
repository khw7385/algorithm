//
// Created by hyunwon on 2024-08-20.
//
#include <iostream>
#include <vector>

using namespace std;

vector<int> parent;

void solve();

int find(int x){
    if(parent[x] == x) return x;
    else return parent[x] = find(parent[x]);
}

void merge(int x, int y){
    int px = find(x);
    int py = find(y);

    if(px != py){
        if(px < py){
            parent[py] = px;
        } else{
            parent[px] = py;
        }
    }
}
int main(void){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    solve();
}

void solve(){
    int n, m;

    cin >> n >> m;

    for (int i = 0; i <= n; ++i) {
        parent.push_back(i);
    }

    int op, x, y;

    for (int i = 0; i < m; ++i) {
        cin >> op >> x >> y;

        if(op == 0){
            merge(x, y);
        }else{
            if(find(x) == find(y)){
                cout << "YES" << '\n';
            }else{
                cout << "NO" << '\n';
            }
        }
    }
}