//
// Created by hyunwon on 2024-07-18.
//
#include <iostream>

#define BUILDING_MAX 50
#define LEFT_INF_GRADIENT 1000000000
#define RIGHT_INF_GRADIENT -1000000000

using namespace std;

int building_num;
long long int buildings[BUILDING_MAX];

void input();
void solve();
int get_watching_building_num(int index);
double get_gradient(pair<int, long long int> a, pair<int, long long int> b);

int main(void){
    input();
    solve();
}
void input(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> building_num;

    for (int i = 0; i < building_num; ++i) {
        cin >> buildings[i];
    }
}
void solve(){
    int result = 0;

    for (int i = 0; i < building_num; ++i) {
        result = max(result, get_watching_building_num(i));
    }

    cout <<result << endl;
}

int get_watching_building_num(int index){
    int num = 0;
    double left_max_gradient = LEFT_INF_GRADIENT;
    double right_max_gradient = RIGHT_INF_GRADIENT;
    double gradient;
    for (int i = index - 1; i >= 0; i--) {
        gradient = get_gradient(make_pair(index, buildings[index]), make_pair(i, buildings[i]));

        if(gradient < left_max_gradient){
            num++;
            left_max_gradient = gradient;
        }
    }
    for (int i = index + 1; i < building_num; ++i) {
        gradient = get_gradient(make_pair(index, buildings[index]), make_pair(i, buildings[i]));

        if(gradient > right_max_gradient){
            num++;
            right_max_gradient = gradient;
        }
    }

    return num;
}

double get_gradient(pair<int, long long int> a, pair<int, long long int> b){
    return (double)(a.second - b.second) / (a.first - b.first);
}