#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int M;
int arr[MAX_N];
int d[MAX_M + 1];

// 초기화
void init() {
	for (int i = 1; i <= M; i++) {
		d[i] = INT_MAX;
	}
}

// dp
void solve() {
	// 기준 m원을 잡고
	for (int m = 1; m <= M; m++) {
		// 동전들을 사용하여
		for (int j = 0; j < N; j++) {
			int coin = arr[j];

			// 기준 m보다 뺄 coin이 더 큰 경우 패쓰
			if (coin > m) continue;
			// m-coin원을 만들 수 없는 경우 패쓰
			if (d[m - coin] == INT_MAX) continue;

			// 최소값 갱신
			d[m] = min(d[m], d[m - coin] + 1);
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	init();

	solve();

	// 답이 없는 경우
	if (d[M] == INT_MAX) d[M] = -1;
	cout << d[M];
}