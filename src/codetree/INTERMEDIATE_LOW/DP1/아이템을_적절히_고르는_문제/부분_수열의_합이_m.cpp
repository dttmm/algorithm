#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_M 10000
#define INF 1000000

int N;
int M;
int arr[MAX_N];
int d[MAX_M + 1];

// 초기화
void init() {
	for (int i = 1; i <= M; i++) {
		d[i] = INF;
	}
}

// dp
void solve() {
	// 원소 하나를 골라서
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// 해당 원소를 포함하여 m을 만들었을 때 최소값 갱신
		for (int m = M; m >= 1; m--) {
			if (n > m) continue;

			d[m] = min(d[m], d[m - n] + 1);
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

	if (d[M] == INF) d[M] = -1;
	cout << d[M];
}