#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 3분 구현 5분
* dp
* 칸을 순회하면서
* 현재 칸에서 갈 수 있는 다음 칸을 탐색하고
* 다음 칸에 도달할 때까지 점프 횟수를 최소값으로 갱신함
* dp배열 초기값을 INF로 설정해서
* 현재 칸이 INF라면
* 앞에서 부터 점프해서 도달할 수 없는 칸이므로
* INF칸은 건너뜀
*/

using namespace std;

#define MAX_N 1000
#define INF 100000000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp배열 초기화
void init() {
	// 0번 인덱스는 시작지점이므로 1번 인덱스부터 초기화
	for (int i = 1; i < N; i++) {
		d[i] = INF;
	}
}

// dp
void solve() {
	// 모든 칸을 순회하면서
	for (int i = 0; i < N; i++) {
		// 도달할 수 없는 칸인 경우
		if (d[i] == INF) continue;

		// 점프할 수 있는 만큼 점프함
		int n = arr[i];
		for (int j = i + 1; j <= i + n; j++) {
			// 범위를 넘어서는 경우
			if (j >= N) break;

			// 도달횟수 업데이트
			d[j] = min(d[j], d[i] + 1);
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11060.txt", "r", stdin);

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	init();

	solve();

	// 마지막 칸에 도달할 수 없는 경우
	if (d[N - 1] == INF) d[N - 1] = -1;

	cout << d[N - 1];
}