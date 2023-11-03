#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp
void solve() {
	// 초기값 세팅
	for (int k = 1; k <= arr[0]; k++) {
		d[k] = 1;
	}

	// 기준 i를 잡고
	for (int i = 1; i < N; i++) {
		// 첫 번째 위치에서부터 못 오는 경우 패쓰
		if (d[i] == 0) continue;

		// 해당 위치에서 점프할 수 있는 곳들 점프하면서 최대값 갱신
		int n = arr[i];
		for (int k = 1; k <= n; k++) {
			d[i + k] = max(d[i + k], d[i] + 1);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// 최대값 찾기
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}