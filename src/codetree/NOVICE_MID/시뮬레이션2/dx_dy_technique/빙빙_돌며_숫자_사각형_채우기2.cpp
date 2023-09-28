#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];
int i;
int j;
int di[] = { 1,0,-1,0 };
int dj[] = { 0,1,0,-1 };

// ���� üũ
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < M);
}

void solve() {
	int dir = 0;
	int num = 1;

	// �ʱ� ��ġ ����
	arr[i][j] = num++;

	while (num <= N * M) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ���� ����ų� �̹� �湫�� ���� ���
		if (!isIn(newI, newJ) || arr[newI][newJ] != 0) {
			dir = (dir + 1) % 4;
			newI = i + di[dir];
			newJ = j + dj[dir];
		}

		// ���� ��ġ ����
		i = newI;
		j = newJ;
		arr[i][j] = num++;
	}
}

int main() {

	cin >> N >> M;

	solve();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}
}