#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int K;
int arr[MAX_N];
int ans = 100;

bool solve(int maxVal) {
	int prev = 0;	// 이전 돌 인덱스

	// 돌 순회
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// 최대값보다 더 큰 돌이면 패쓰
		if (n > maxVal) continue;

		// K이내에 갈 수 없으면 maxVal은 정답이 아님
		if (i - prev > K) return false;

		prev = i;
	}

	return true;
}

int main() {

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	for (int i = 0; i < N; i++) {
		int n = arr[i]; // 최대값으로 설정할 값
		if (n < arr[0]) continue; // 시작점 보다는 커야됨
		if (n < arr[N - 1]) continue; // 도착점 보다도 커야됨

		bool ret = solve(n);

		if (ret) ans = min(ans, n);
	}

	cout << ans;
}