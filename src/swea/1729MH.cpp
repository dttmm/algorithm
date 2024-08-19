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
Node target;	// ���� ���� ����

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

		// ������ ���ſ� ������ ���
		if (node.isS && i == target.i && j == target.j) return node.time;

		for (int dir = 0; dir < 4; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// ������ ��� ���
			if (!isIn(newI, newJ)) continue;
			// ���̰ų� �Ǹ��� ���
			if (arr[newI][newJ] == 1) continue;
			// �����̰� �湮�� ���� ���
			if (node.isS && visited[newI][newJ]) continue;
			// �Ǹ��ε� �������� ���� ���
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

		// ���� ���� ����
		Node su;
		for (int i = 0; i < N; i++) {
			string s;
			cin >> s;

			for (int j = 0; j < M; j++) {
				char c = s[j];

				// ����
				if (c == 'D') {
					target = Node(i, j, 0, false);
				}
				// �Ǹ�
				else if (c == '*') {
					q.emplace(Node(i, j, 0, false));
					arr[i][j] = 1;
				}
				// ����
				else if (c == 'S') {
					visited[i][j] = true;
					su = Node(i, j, 0, true);
				}
				// ��
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