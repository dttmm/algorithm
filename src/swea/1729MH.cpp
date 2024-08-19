#include <iostream>
#include <queue>
#include <vector>
#include <string>

using namespace std;

/*
* in 1729 out 7793
*/

#define MAX_N 50

struct Node {
	int i;
	int j;
	int time;
	bool isS;

	Node() {}
	Node(int i_, int j_, int time_, int isS_) {
		i = i_;
		j = j_;
		time = time_;
		isS = isS_;
	}
};

int nodeIndex;
int N;
int M;
int arr[MAX_N][MAX_N];
bool visited[MAX_N][MAX_N];
int di[4] = { -1,0,0,1 };
int dj[4] = { 0,1,-1,0 };
queue<Node> q;
Node target;	// 여신 정보 저장

void init() {
	nodeIndex = 0;
	q = queue<Node>();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			arr[i][j] = 0;
			visited[i][j] = false;
		}
	}
}

bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < M;
}

int solve() {
	while (!q.empty()) {
		Node node = q.front();
		q.pop();

		int i = node.i;
		int j = node.j;

		// 수연이 여신에 도달한 경우
		if (node.isS && i == target.i && j == target.j) return node.time;

		for (int dir = 0; dir < 4; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// 범위를 벗어난 경우
			if (!isIn(newI, newJ)) continue;
			// 벽이거나 악마인 경우
			if (arr[newI][newJ] == 1) continue;
			// 수연이가 방문한 곳인 경우
			if (node.isS && visited[newI][newJ]) continue;
			// 악마인데 여신한테 가는 경우
			if (!node.isS && newI == target.i && newJ == target.j) continue;

			if (node.isS) visited[newI][newJ] = true;
			arr[newI][newJ] = arr[i][j];
			q.emplace(Node(newI, newJ, node.time + 1, node.isS));
		}
	}

	return -1;
}

int main() {

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> M;

		init();

		// 수연 정보 저장
		Node su;
		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;

			for (int j = 0; j < M; j++) {
				char c = s[j];

				// 여신
				if (c == 'D') {
					target = Node(i, j, 0, false);
				}
				// 악마
				else if (c == '*') {
					q.emplace(Node(i, j, 0, false));
					arr[i][j] = 1;
				}
				// 수연
				else if (c == 'S') {
					visited[i][j] = true;
					su = Node(i, j, 0, true);
				}
				// 벽
				else if (c == 'X') {
					arr[i][j] = 1;
				}
			}
		}

		q.emplace(su);

		int ret = solve();

		if (ret == -1) cout << "#" << tc << " GAME OVER" << "\n";
		else cout << "#" << tc << " " << ret << "\n";
	}
}