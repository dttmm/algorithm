#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 1000

int N;
bool arr[MAX_N];
int ans;

// 두 사람 거리 중 최소값 구하기
int solve() {
	int minVal = MAX_N;
	for (int i = 0; i < N; i++) {
		if (!arr[i]) continue;
		for (int j = i + 1; j < N; j++) {
			if (!arr[j]) continue;

			int diff = j - i;
			minVal = min(minVal, diff);
			break;
		}
	}

	return minVal;
}

int main() {

	string s;
	cin >> N >> s;
	for (int i = 0; i < N; i++) {
		if (s[i] == '1') arr[i] = true;
	}

	// 가장 먼 두 사람간의 거리 구하기
	int maxVal = 0;
	int maxI, maxJ;
	for (int i = 0; i < N; i++) {
		if (!arr[i]) continue;
		for (int j = i + 1; j < N; j++) {
			if (!arr[j]) continue;

			int diff = j - i;
			if (diff > maxVal) {
				maxVal = diff;
				maxI = i;
				maxJ = j;
			}
			break;
		}
	}

	// 가장 거리가 먼 두 지점 사이에 놓고 거리 구하기
	if (maxVal != 0) {
		int mid = (maxI + maxJ) / 2;
		arr[mid] = true;

		int ret = solve();
		arr[mid] = false;

		ans = max(ans, ret);
	}

	// 첫번째 위치에 놓고 거리 구하기
	if (!arr[0]) {
		arr[0] = true;
		int ret = solve();
		ans = max(ans, ret);
		arr[0] = false;
	}
	// 마지막 위치에 놓고 거리 구하기
	if (!arr[N - 1]) {
		arr[N - 1] = true;
		int ret = solve();
		ans = max(ans, ret);
		arr[N - 1] = false;
	}

	cout << ans;
}