#include <iostream>
#include <stack>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];	// �迭 ���� �� �湮 ó��
int di[] = { 1,0 };
int dj[] = { 0,1 };

// ���� üũ
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

		// ������ �ϴܿ� ������ ���
		if (i == N - 1 && j == M - 1) return true;

		// 2���� Ž��
		for (int dir = 0; dir < 2; dir++) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// ���� ����� �о�
			if (!isIn(newI, newJ)) continue;
			// ���� �ְų� �̹� �湮������ �о�
			if (arr[newI][newJ] == 0) continue;

			arr[newI][newJ] = 0;
			st_i.push(newI);
			st_j.push(newJ);
		}
	}

	return false;
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}

	bool ret = solve();

	cout << ret;
}