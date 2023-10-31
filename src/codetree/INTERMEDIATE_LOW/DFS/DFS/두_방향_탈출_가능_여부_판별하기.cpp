#include <iostream>
#include <stack>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];	// 배열 정보 및 방문 처리
int di[] = { 1,0 };
int dj[] = { 0,1 };

// 범위 체크
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < M;
}

// dfs
bool solve() {
	stack<int> st_i;
	stack<int> st_j;
	st_i.push(0);
	st_j.push(0);

	while (!st_i.empty()) {
		int i = st_i.top();
		int j = st_j.top();
		st_i.pop();
		st_j.pop();

		// 오르쪽 하단에 도달한 경우
		if (i == N - 1 && j == M - 1) return true;

		// 2방향 탐색
		for (int dir = 0; dir < 2; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// 범위 벗어나면 패쓰
			if (!isIn(newI, newJ)) continue;
			// 뱀이 있거나 이미 방문했으면 패쓰
			if (arr[newI][newJ] == 0) continue;

			arr[newI][newJ] = 0;
			st_i.push(newI);
			st_j.push(newJ);
		}
	}

	return false;
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}

	bool ret = solve();

	cout << ret;
}