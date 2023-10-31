#include <iostream>
#include <stack>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N];
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };
int ansCnt;
int ansMax;

// 범위 체크
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

// dfs
int dfs(int i, int j, int cur) {
	int ret = 0;	// 현재 위치 다음에 총 몇 개의 노드 거쳤는지

	// 4방향 탐색
	for (int dir = 0; dir < 4; dir++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어나면 패쓰
		if (!isIn(newI, newJ)) continue;
		// 이미 방문했으면 패쓰
		if (visited[newI][newJ]) continue;
		// 같은 숫자 아닌경우 패쓰
		if (arr[newI][newJ] != cur) continue;

		visited[newI][newJ] = true;

		ret += dfs(newI, newJ, cur);
	}

	// 현재 위치 포함하여 총 거쳐간 노드 개수 리턴
	return ret + 1;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	// 모든 노드 검사
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// 이미 방문해씅면 패쓰
			if (visited[i][j]) continue;
			visited[i][j] = true;

			// dfs 거쳐간 노드 개수 리턴받음
			int ret = dfs(i, j, arr[i][j]);
			// 4개 이상인 경우
			if (ret >= 4) ansCnt++;

			ansMax = max(ansMax, ret);
		}
	}

	cout << ansCnt << " " << ansMax;
}