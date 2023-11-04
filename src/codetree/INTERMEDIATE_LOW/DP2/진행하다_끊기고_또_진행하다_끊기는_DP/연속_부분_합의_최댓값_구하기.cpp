#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
int d[MAX_N];	// i가 마지막 원소일 때 최대값

void solve() {
	d[0] = arr[0];

	// 이전 결과에서 자신을 마지막 원소로 추가하거나
	// 자신부터 시작하거나 둘 중 최대값 고름
	for (int i = 1; i < N; i++) {
		d[i] = max(d[i - 1] + arr[i], arr[i]);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	int ans = -10000;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}