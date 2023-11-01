#include <iostream>
#include <queue>
#include <algorithm>
#include <tuple>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];
int depth[MAX_N][MAX_N];
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

// 범위 체크
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < M;
}

// bfs
void solve() {
	queue<pair<int, int>> q;
	q.push(make_pair(0, 0));
	arr[0][0] = 0;

	while (!q.empty()) {
		int i, j;
		tie(i, j) = q.front();
		q.pop();

		// 4방향 탐색
		for (int dir = 0; dir < 4; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// 범위 벗어나면 패쓰
			if (!isIn(newI, newJ))continue;
			// 뱀이거나 이미 방문했으면 패쓰
			if (arr[newI][newJ] == 0) continue;
			arr[newI][newJ] = 0;

			q.push(make_pair(newI, newJ));
			depth[newI][newJ] = depth[i][j] + 1;
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}

	solve();

	if (depth[N - 1][M - 1] == 0) cout << -1;
	else cout << depth[N - 1][M - 1];
}