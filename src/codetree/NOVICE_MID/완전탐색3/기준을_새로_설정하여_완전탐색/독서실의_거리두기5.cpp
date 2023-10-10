#include <iostream>
#include <algorithm>
#include <string>
#include <cstdlib>

using namespace std;

#define MAX_N 20
#define INF 1000

int N;
char arr[MAX_N];
int ans;

// 1명 자리에 채워 넣기
void solve(int k) {
	if (k == 1) {
		int left[MAX_N];	// 왼쪽에서 나랑 제일 가까운 놈
		int right[MAX_N];	// 오른쪽에서 나랑 제일 가까운 놈

		fill(left, left + N, INF);
		fill(right, right + N, INF);

		// 왼쪽에서 나랑 제일 가까운 놈 구하기
		for (int i = 1; i < N; i++) {
			left[i] = left[i - 1];
			if (arr[i - 1] == 1) left[i] = i - 1;
		}

		// 오른쪽에서 나랑 제일 가까운 놈 구하기
		for (int i = N - 2; i >= 0; i--) {
			right[i] = right[i + 1];
			if (arr[i + 1] == 1) right[i] = i + 1;
		}

		// 가장 가까운 놀들의 거리 구하기
		int minVal = INF;
		for (int i = 0; i < N; i++) {
			if (arr[i] != 1) continue;
			int diff1 = abs(i - left[i]);
			int diff2 = abs(i - right[i]);

			minVal = min(minVal, diff1);
			minVal = min(minVal, diff2);
		}

		// 최대값 갱신
		ans = max(ans, minVal);
	}
	else {
		for (int i = 0; i < N; i++) {
			if (arr[i] == 1) continue;
			arr[i] = 1;
			solve(k + 1);
			arr[i] = 0;
		}
	}
}

int main() {

	// 입력
	string s;
	cin >> N >> s;
	for (int i = 0; i < s.length(); i++) {
		arr[i] = s[i] - '0';
	}

	solve(0);

	cout << ans;
}