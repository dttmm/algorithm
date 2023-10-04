#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 50
#define MAX_D 1000

int N;
int M;
int D;
int S;
int arr[MAX_N + 1][MAX_N + 1];	// i번 치즈를 j번 사람이 value초에 먹음
int brr[MAX_N + 1];				// i번 사람이 value초에 아픔
int ans;

// i번 치즈 먹은사람 수 리턴
int getCnt(int i) {
	int cnt = 0;
	for (int j = 1; j <= N; j++) {
		if (arr[i][j] > 0) cnt++;
	}
	return cnt;
}

void solve() {
	// i: 치즈
	for (int i = 1; i <= M; i++) {
		bool flag = true;	// 상할 가능성 플래그. true: 상할 가능성 있음

		// j: i번 치즈를 value초에 먹은 사람
		for (int j = 1; j <= N; j++) {
			// 아픈 기록 없는 사람 패쓰
			if (brr[j] == 0) continue;

			// 해당 치즈를 안먹었는데 아픈 사람이 있는 경우 -> i번 치즈는 상하지 않음
			if (arr[i][j] == 0) {
				flag = false;
				break;
			}

			// 아픈 시간이 먹은 시간보다 더 이른 경우 -> i번 치즈는 상하지 않음
			if (brr[j] <= arr[i][j]) {
				flag = false;
				break;
			}
		}

		// 아픈 기록을 만족하는 경우 -> 상한 치즈가 될 수 있는 후보
		if (flag) {
			// 해당 치즈를 먹은 사람 구함
			int ret = getCnt(i);
			ans = max(ans, ret);
		}
	}
}

int main() {

	// 입력 받기
	cin >> N >> M >> D >> S;
	for (int i = 0; i < D; i++) {
		int n;
		int c;
		int t;
		cin >> n >> c >> t;

		if (arr[c][n] == 0) arr[c][n] = t;
		else arr[c][n] = min(arr[c][n], t);

	}

	for (int i = 0; i < S; i++) {
		int n;
		int t;
		cin >> n >> t;

		brr[n] = t;
	}

	solve();

	cout << ans;
}