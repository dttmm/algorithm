#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 10000

int N;
int K;
int cnt[MAX_X + 2];		// 숫자의 개수
int ans = MAX_X * MAX_N;

void solve() {
	// 범위를 미리 설정함
	int minVal = 0;				// 최소값
	int maxVal = minVal + K;	// 최대값

	int cntL = 0;	// 최소값보다 작은 놈들의 수
	int cntR = 0;	// 최대값보다 큰 놈들의 수

	// 범위 안에 들기 위한 비용의 합
	int sum = 0;
	// 최대값보다 큰 놈들의 합 구하기
	for (int i = maxVal + 1; i <= MAX_X; i++) {
		sum += cnt[i] * (i - maxVal);
		cntR += cnt[i];
	}
	ans = min(ans, sum);

	// 범위를 이동시켜가며 범위 안에 들기 위한 합 구하기 (슬라이딩 윈도우)
	while (maxVal <= MAX_X) {
		// 이제 최소값이 1커짐에 따라 기존 최소값은 방출?됨
		cntL += cnt[minVal];

		// 비용 업데이트
		sum += cntL;
		sum -= cntR;

		// 최대값이 1커짐에 따라 최대값보다 1큰 녀석은 범위 안에 포함됨
		cntR -= cnt[maxVal + 1];

		// 범위 이동
		minVal++;
		maxVal++;

		ans = min(ans, sum);
	}

}

int main() {

	// 입력
	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;
		cnt[n]++;
	}

	solve();

	cout << ans;
}