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

// visited �ʱ�ȭ
void initVisited() {
	fill_n(visited[0], MAX_N * MAX_N, false);
}

// ���� üũ
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

// ���ο� ������ �켱������ �� ������ üũ
bool isHigh(int i, int j, int newI, int newJ) {
	int n = arr[i][j];
	int newN = arr[newI][newJ];

	tuple<int, int, int> t = make_tuple(n, -i, -j);	// ���� ����
	tuple<int, int, int> newT = make_tuple(newN, -newI, -newJ);	// ���ο� ����

	return newT > t;
}

// bfs
pair<int, int> bfs(pair<int, int> p) {
	int start_i, start_j;
	tie(start_i, start_j) = p;
	int stadard = arr[start_i][start_j];

	queue<pair<int, int>> q;
	pair<int, int> bestP = make_pair(-1, -1);	// �켱������ ���� ���� ����

	q.push(p);
	visited[start_i][start_j] = true;

	while (!q.empty()) {
		int i, j;
		tie(i, j) = q.front();
		q.pop();

		// 4���� Ž��
		for (int dir = 0; dir < 4; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// ���� ����� �о�
			if (!isIn(newI, newJ)) continue;
			// �̹� �湮������ �о�
			if (visited[newI][newJ])continue;
			visited[newI][newJ] = true;
			// ���������� ũ�ų� ������ �о�
			if (arr[newI][newJ] >= arr[start_i][start_j]) continue;

			q.push(make_pair(newI, newJ));

			// ù��° �湮�� ���
			if (bestP.first == -1) {
				bestP = make_pair(newI, newJ);
				continue;
			}

			// �켱���� ���� ���� ����
			bool ret = isHigh(bestP.first, bestP.second, newI, newJ);
			if (ret) bestP = make_pair(newI, newJ);
		}
	}
	return bestP;
}

pair<int, int> solve(int i, int j) {
	pair<int, int> p = make_pair(i, j);

	// k�� �ݺ�
	while (K--) {
		initVisited();

		// �̵��� ��ġ ��ȯ ����
		pair<int, int> retP = bfs(p);

		// �� �̻� �̵��� ���ϴ� ���
		if (retP.first == -1 && retP.second == -1) break;

		p = retP;
	}

	return p;
}

int main() {

	// �Է�
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