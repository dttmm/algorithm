#include <iostream>
#include <algorithm>
#include <climits>
#include <cmath>

using namespace std;

#define MAX_N 100
#define MAX_A 1000

int N;
int cntArr[MAX_A + 1];
int startArr[MAX_N];
int endArr[MAX_N];
int ans = 0;

// 운행되고 있는 시간 세기
int getCnt() {
	int cnt = 0;
	for (int i = 0; i <= MAX_A; i++) {
		if (cntArr[i] > 0) cnt++;
	}
	return cnt;
}

// 1명 해고하기
void solve() {
	for (int i = 0; i < N; i++) {
		int start = startArr[i];
		int end = endArr[i];

		// 해고한 만큼 시간 마이너스
		for (int j = start; j < end; j++) {
			cntArr[j]--;
		}

		// 운행되고 있는 시간 세기
		int ret = getCnt();
		ans = max(ans, ret);

		// 다시 시간 복구
		for (int j = start; j < end; j++) {
			cntArr[j]++;
		}
	}
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start;
		int end;
		cin >> start >> end;

		startArr[i] = start;
		endArr[i] = end;
		for (int j = start; j < end; j++) {
			cntArr[j]++;
		}
	}

	// 1명 뽑기
	solve();

	cout << ans;
}