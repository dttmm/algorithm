#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_M 1000000
#define INF 1000000000

int N;
int M;
int d[MAX_M + 1];	// 경험치 m을 얻는데 걸리는 최소 시간

struct Node {
	int e;
	int t;
}nodes[MAX_N];

// 초기화
void init() {
	for (int i = 1; i <= MAX_M; i++) {
		d[i] = INF;
	}
}

// dp
void solve() {
	// 퀘스트 i를 수행할때
	for (int i = 0; i < N; i++) {
		int e = nodes[i].e;
		int t = nodes[i].t;

		// 얻을 수 있는 경험치까지 걸리는 데 최소를 갱신
		for (int m = MAX_M; m >= 1; m--) {
			if (e > m) continue;

			d[m] = min(d[m], d[m - e] + t);
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int e, t;
		cin >> e >> t;

		nodes[i].e = e;
		nodes[i].t = t;
	}

	init();

	solve();

	// 경험치 M이상 얻는데 걸리는 최소 시간 구하기
	int ans = INF;
	for (int i = M; i <= MAX_M; i++) {
		ans = min(ans, d[i]);
	}

	if (ans == INF) ans = -1;
	cout << ans;
}