#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_H 1000

int N;
int arr[MAX_N];
int ans;

// 완탐
void solve() {
	// 가능한 높이 돌면서
	for (int h = 1; h < MAX_H; h++) {
		int cnt = 0;	// 빙산 덩어리 개수
		bool flag = false;	// 이전에 빙상이었었는지 플래그

		// 얼음덩어리 순회
		for (int j = 0; j < N; j++) {
			int n = arr[j] - h;

			// 얼음의 높이가 해수면보다 높은 경우
			if (n > 0)
				flag = true;
			// 얼음의 높이가 해수면보다 낮은 경우
			else {
				// 이전에 빙산이었던 경우
				if (flag) cnt++;
				flag = false;
			}
		}

		// 마지막 빙산 처리
		if (flag) cnt++;

		ans = max(ans, cnt);

		if (cnt == 0) break;
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << ans;
}