#include <iostream>
#include <fstream>

/*
* 설계 1분 구현 1분
* dp
* 간단한 dp문제
* 바로 이전 결과에 1을 붙여줄 수 있고
* 이이전 결과에 00을 붙여줄 수 있으므로
* 점화식은 다움과 같이 나옴
* d[i] = d[i - 1] + d[i - 2]
*/

using namespace std;

#define MAX_N 1000000
#define MOD 15746

int N;
int d[MAX_N + 1];

void solve() {
	d[1] = 1;
	d[2] = 2;

	for (int i = 3; i <= N; i++) {
		d[i] = (d[i - 1] + d[i - 2]) % MOD;
	}
}

int main() {

	freopen("res/baekjoon/1904.txt", "r", stdin);

	cin >> N;

	solve();

	cout << d[N];
}