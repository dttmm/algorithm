#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define INF 100000000

int N;
int arr[MAX_N + 1][MAX_N + 1];
int d[MAX_N + 1][MAX_N + 1];

// dp
void solve() {
	// 첫행, 막열 초기화
	for (int i = 0; i < N; i++) {
		d[0][i] = INF;
		d[i + 1][N] = INF;
	}
	// 시작 지점 바로 위 초기화 <- 시작 지점에서 최소값은 자기 자신이 되야되기 때문
	d[0][N - 1] = 0;

	for (int i = 1; i <= N; i++) {
		for (int j = N - 1; j >= 0; j--) {
			// 위쪽과 오른쪽에서만 현재지점으로 올 수 있음
			d[i][j] = min(d[i - 1][j], d[i][j + 1]) + arr[i][j];
		}
	}
}

int main() {

	// 입력
	cin >> N;

	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	solve();

	cout << d[N][0];
}