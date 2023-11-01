#include <iostream>
#include <queue>
#include <algorithm>
#include <tuple>

using namespace std;

#define MAX_N 100

int N;
int K;
int arr[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N];
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

// visited 초기화
void initVisited() {
	fill_n(visited[0], MAX_N * MAX_N, false);
}

// 범위 체크
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

// 새로운 지점이 우선순위가 더 높은지 체크
bool isHigh(int i, int j, int newI, int newJ) {
	int n = arr[i][j];
	int newN = arr[newI][newJ];

	tuple<int, int, int> t = make_tuple(n, -i, -j);	// 기존 지점
	tuple<int, int, int> newT = make_tuple(newN, -newI, -newJ);	// 새로운 지점

	return newT > t;
}

// bfs
pair<int, int> bfs(pair<int, int> p) {
	int start_i, start_j;
	tie(start_i, start_j) = p;
	int stadard = arr[start_i][start_j];

	queue<pair<int, int>> q;
	pair<int, int> bestP = make_pair(-1, -1);	// 우선순위가 제일 높은 지점

	q.push(p);
	visited[start_i][start_j] = true;

	while (!q.empty()) {
		int i, j;
		tie(i, j) = q.front();
		q.pop();

		// 4방향 탐색
		for (int dir = 0; dir < 4; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// 범위 벗어나면 패쓰
			if (!isIn(newI, newJ)) continue;
			// 이미 방문했으면 패쓰
			if (visited[newI][newJ])continue;
			visited[newI][newJ] = true;
			// 시작접보다 크거나 같으면 패쓰
			if (arr[newI][newJ] >= arr[start_i][start_j]) continue;

			q.push(make_pair(newI, newJ));

			// 첫번째 방문인 경우
			if (bestP.first == -1) {
				bestP = make_pair(newI, newJ);
				continue;
			}

			// 우선순위 높은 지점 갱신
			bool ret = isHigh(bestP.first, bestP.second, newI, newJ);
			if (ret) bestP = make_pair(newI, newJ);
		}
	}
	return bestP;
}

pair<int, int> solve(int i, int j) {
	pair<int, int> p = make_pair(i, j);

	// k번 반복
	while (K--) {
		initVisited();

		// 이동할 위치 반환 받음
		pair<int, int> retP = bfs(p);

		// 더 이상 이동을 안하는 경우
		if (retP.first == -1 && retP.second == -1) break;

		p = retP;
	}

	return p;
}

int main() {

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	int i, j;
	cin >> i >> j;

	pair<int, int> retP = solve(i - 1, j - 1);

	cout << retP.first + 1 << " " << retP.second + 1;
}