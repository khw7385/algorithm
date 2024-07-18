//
// Created by hyunwon on 2024-07-15.
//
#include <iostream>

using namespace std;

int N, M;
bool arr[10];
int now = 100;

void input();
void solve();

bool is_possible(int num){
    string str = to_string(num);
    for(int i = 0; i < str.size(); i++){
        if(!arr[str[i] - '0']) return false;
    }
    return true;
}

int main(){
    input();
    solve();
    return 0;
}

void input(){
    cin >> N;
    cin >> M;

    int temp;

    fill_n(arr, 10, true);
    for (int i = 0; i < M; ++i) {
        cin >> temp;
        arr[temp] = false;
    }
}

void solve(){
    if(N == 100) {
        cout << 0 << endl;
        return;
    }
    int ans = abs(N - 100);

    for (int i = 0; i < 1000000; ++i) {
        if(is_possible(i)){
            int temp = abs(N - i) + to_string(i).size();
            ans = min(ans, temp);
        }
    }

    cout << ans << endl;
}


