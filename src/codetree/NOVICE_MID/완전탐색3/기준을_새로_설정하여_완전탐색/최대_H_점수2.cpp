#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 100

int N;
int L;
int cnt[MAX_N + 2];	// 숫자 i의 개수
int sum[MAX_N + 1];	// 숫자 i보다 크거나 같은 놈 개수(누적합)

// 누적합 구하기
void setSum() {
	for (int x = MAX_X; x >= 0; x--) {
		sum[x] = sum[x + 1] + cnt[x];
	}
}

int main() {

	// 입력
	cin >> N >> L;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;
		cnt[n]++;
	}

	// 누적합
	setSum();

	// x를 정답이라고 했을 때 조건을 만족하는지 검사
	for (int x = MAX_X; x >= 1; x--) {
		// x보다 크거나 같은놈의 개수와 +1이 가능한 x-1의 개수의 합이 x를 넘어야 함
		int total = sum[x] + min(cnt[x - 1], L);

		// 정답 조건을 만족하는 경우
		if (total >= x) {
			cout << x;
			break;
		}
	}
}