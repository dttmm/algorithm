#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int arr[] = { 1,2,5 };
int d[MAX_N + 1];

void solve() {
	// 초기값 세팅
	d[0] = 1;

	// n을 만들기 위해
	for (int n = 1; n <= N; n++) {
		// 1,2,5를 사용했을 때 만들수 있는 경우의 수 더함
		for (int i = 0; i < 3; i++) {
			int m = arr[i];
			if (m > n) continue;

			d[n] = (d[n] + d[n - m]) % MOD;
		}
	}
}

int main() {

	// 입력
	cin >> N;

	solve();

	cout << d[N];
}