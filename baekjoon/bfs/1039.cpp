//
// Created by hyunwon on 2024-07-22.
//
#include <iostream>
#include <queue>
#include <set>

using namespace std;

string str; // 길이를 편하게 알기 위해서
int K;
bool isSame = false;

void input();
void solve();
void swap(string& s, int left, int right){
    char temp = s[left];
    s[left] = s[right];
    s[right] = temp;
}

int main(void){
    input();
    solve();
    return 0;
}

void input(){
    cin >> str >> K;
}
void solve(){
    queue<string> q;
    q.push(str);

    for (int i = 0; i < K; ++i) {
        set<string> s;
        int q_size = q.size();

        for (int j = 0; j < q_size; ++j) {
            string item = q.front(); q.pop();
            if(s.find(item) != s.end()) continue;
            s.insert(item);

            for (int k = 0; k < item.size(); ++k) {
                for (int l = k + 1; l < item.size(); ++l) {
                    if(k == 0 && item[l] == '0') continue;
                    swap(item, k, l);
                    q.push(item);
                    swap(item, k, l);
                }
            }
        }
    }

    string result = "0";

    while(!q.empty()){
        result = max(result, q.front());
        q.pop();
    }

    if(result[0] == '0') cout << -1 << endl;
    else cout << result << endl;
}
