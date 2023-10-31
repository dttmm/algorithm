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

// ���� üũ
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

// dfs
int dfs(int i, int j, int cur) {
	int ret = 0;	// ���� ��ġ ������ �� �� ���� ��� ���ƴ���

	// 4���� Ž��
	for (int dir = 0; dir < 4; dir++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ���� ����� �о�
		if (!isIn(newI, newJ)) continue;
		// �̹� �湮������ �о�
		if (visited[newI][newJ]) continue;
		// ���� ���� �ƴѰ�� �о�
		if (arr[newI][newJ] != cur) continue;

		visited[newI][newJ] = true;

		ret += dfs(newI, newJ, cur);
	}

	// ���� ��ġ �����Ͽ� �� ���İ� ��� ���� ����
	return ret + 1;
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	// ��� ��� �˻�
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// �̹� �湮�؝��� �о�
			if (visited[i][j]) continue;
			visited[i][j] = true;

			// dfs ���İ� ��� ���� ���Ϲ���
			int ret = dfs(i, j, arr[i][j]);
			// 4�� �̻��� ���
			if (ret >= 4) ansCnt++;

			ansMax = max(ansMax, ret);
		}
	}

	cout << ansCnt << " " << ansMax;
}