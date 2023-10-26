#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 3분 구현 3분
* dp
* 무제를 보자마다 N^2으로 풀어야 겠다는 생각이 들었음
* 먼저 dp배열에 i번째 숫자까지 포함했을 때 부분수열 합의 최대값을 담음
* 그리고 i이전의 숫자들을 돌면서 자신보다 작은 숫자를 만난경우
* 작은 숫자들의 dp값들 중에서 최대값을 골라서
* 자신을 더한다면
* i번째 숫자까지 포함했을 때의 최대값이 나옴
*/

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp
void solve() {
	// 기준 i를 정하고
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		int maxVal = 0;

		// 기준보다 이전 값들 탐색
		for (int j = i - 1; j >= 0; j--) {
			int m = arr[j];

			// 자신보다 작은 숫자를 만난 경우
			if (m < n) {
				// 해당 숫자들 중에서 dp최대값 찾음
				maxVal = max(maxVal, d[j]);
			}
		}

		// 최대값에 자신을 포함시켜 dp값 업데이트
		d[i] = maxVal + n;
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11055.txt", "r", stdin);

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// dp배열에서 최대값 찾기
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}