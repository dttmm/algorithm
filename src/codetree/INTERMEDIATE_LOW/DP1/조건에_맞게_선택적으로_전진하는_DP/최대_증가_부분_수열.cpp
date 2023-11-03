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
	d[0] = 1;

	// 기준 i보다 작은 왼쪽에 있는 수들 중에서
	// i보다 작은 것들 중에서 dp최대값 찾아서
	for (int i = 1; i < N; i++) {

		int maxVal = 0;
		for (int j = 0; j < i; j++) {
			if (arr[j] >= arr[i]) continue;
			maxVal = max(maxVal, d[j]);
		}
		// i를 포함시키고 값 업데이트
		d[i] = maxVal + 1;
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// dp배열 중 최대값 찾기
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}