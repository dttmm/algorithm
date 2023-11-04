#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 200

int N;
int M;
int d[MAX_N + 1][MAX_N];	// m일날 i옷을 입었을 때의 최대 만족도

struct Node {
	int s;
	int e;
	int v;
}nodes[MAX_N];

void solve() {
	// 초기값 세팅
	for (int i = 0; i < N; i++) {
		int s = nodes[i].s;

		if (s != 1) d[1][i] = -1;
	}

	// m일일 때
	for (int m = 2; m <= M; m++) {
		// 각 옷을 입을 수 있는지 확인
		for (int i = 0; i < N; i++) {
			int s = nodes[i].s;
			int e = nodes[i].e;
			int v = nodes[i].v;

			// 옷을 입을 수 없는 경우
			if (s > m || e < m) {
				d[m][i] = -1;
				continue;
			}

			// 전날에 입었던 옷들 확인
			for (int j = 0; j < N; j++) {
				// 전날에 입을 수 없었던 옷인 경우
				if (d[m - 1][j] == -1) continue;

				// 전날에 입었던 옷과의 만족도 계산
				int diff = abs(nodes[j].v - v);

				// 전날에 j를 입었을 때의 최대 만족도 + 현재 옷과의 만족도 계산하여
				// 현재 i옷을 입었을 떄의 최대 만족도 갱신
				d[m][i] = max(d[m][i], d[m - 1][j] + diff);
			}
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int s, e, v;
		cin >> s >> e >> v;

		nodes[i].s = s;
		nodes[i].e = e;
		nodes[i].v = v;
	}

	solve();

	// M일날 가능한 최대 만족도 찾기
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[M][i]);
	}

	cout << ans;
}