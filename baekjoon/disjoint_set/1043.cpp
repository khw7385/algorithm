//
// Created by hyunwon on 2024-08-14.
//
#include <iostream>
#include <vector>

#define M_MAX 51

using namespace std;

int n, m;
int parent[M_MAX];
vector<int> party[M_MAX];


int find(int num);
void merge(int num1, int num2);
void solve();

int main(void){
    solve();
}

int find(int num){
    if(num == parent[num]) return num;
    else return parent[num] = find(parent[num]);
}

void merge(int num1, int num2){
    int p1 = find(num1);
    int p2 = find(num2);

    if(p1 != p2){
        if(p1 < p2){
            parent[p2] = p1;
        } else{
            parent[p1] = p2;
        }
    }
}
void solve(){
    int truth, truth_num;
    int party_num, p_first, p_rest;

    // n: 사람 수, m: 파티 수
    cin >> n >> m;

    // 집합 초기화, 파티 집합, 0이면 진실을 아는 그룹에 속함
    for (int i = 0; i <= n; ++i) {
        parent[i] = i;
    }
    // 진실을 아는 사람의 수
    cin >> truth_num;

    for (int i = 0; i < truth_num; ++i) {
        cin >> truth;
        parent[truth] = 0;
    }

    for (int i = 1; i <= m; ++i) {
        cin >> party_num;

        cin >> p_first;

        party[i].push_back(p_first);

        for (int j = 0; j < party_num - 1; ++j) {
            cin >> p_rest;

            party[i].push_back(p_rest);

            merge(p_first, p_rest);
        }
    }
    int ans = m;

    for (int i = 1; i <= m; ++i) {
        for (int j = 0; j < party[i].size(); ++j) {
            if(find(party[i][j]) == 0){
                ans--;
                break;
            }
        }
    }

    cout << ans << endl;
}