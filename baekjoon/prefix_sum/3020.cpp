//
// Created by hyunwon on 2024-03-18.
//
#include <iostream>

#define INF 200001;

using namespace std;

int top[500001];
int bottom[500001];

int main(){
    int N, H;
    int length;
    
    cin >> N >> H;

    for (int i = 0; i < N; ++i) {
        cin >> length;
        if(i % 2 == 0) bottom[length] += 1 ; // 석순이라면
        else top[H - length + 1] += 1; // 종유석이라면
    }

    for (int i = 1; i <= H; ++i) {
        top[i] += top[i - 1];
        bottom[H - i] += bottom[H - i + 1];
    }

    long long int result = INF;
    int cnt = 0;

    for (int i = 0; i < H; ++i) {
        if(top[i] + bottom[i] < result){
            result = top[i] + bottom[i];
            cnt = 1;
        }else if(top[i] + bottom[i] == result) cnt += 1;
    }
    cout << result << " " << cnt << endl;

    return 0;
}