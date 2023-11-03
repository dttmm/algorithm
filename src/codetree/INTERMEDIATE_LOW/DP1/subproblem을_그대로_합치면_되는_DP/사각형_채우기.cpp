#include <iostream>

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int d[MAX_N + 1];

// dp
void solve() {
	d[1] = 1;
	d[2] = 2;

	// 'ㅣ' 모양은 바로 이전 사각형 뒤에 추가할 수 있고
	// 'ㅡ' 모양은 두번째 전 사각형 뒤에 추가할 수 있음
	for (int i = 3; i <= N; i++) {
		d[i] = (d[i - 1] + d[i - 2]) % MOD;
	}
}

int main() {

	// 입력
	cin >> N;

	solve();

	cout << d[N];
}