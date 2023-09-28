#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 19

int N = MAX_N;
int arr[MAX_N][MAX_N];
int di[] = { -1,-1,0,1,1,1,0,-1 };
int dj[] = { 0,1,1,1,0,-1,-1,-1 };
int ansI;
int ansJ;

// ���� üũ
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

// 8���� �˻�
int solve(int start_i, int start_j, int color) {
	for (int dir = 0; dir < 8; dir++) {
		int n = 4;
		int i = start_i;
		int j = start_j;

		// ���� �������� dir �������� 4ĭ �˻�
		while (n > 0) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// ���� ��� ���
			if (!isIn(newI, newJ)) break;
			// ���� �ٸ� ���
			if (arr[newI][newJ] != color) break;

			i = newI;
			j = newJ;
			n--;

			// ������ �߰� ����
			if (n == 2) {
				ansI = i;
				ansJ = j;
			}
		}

		// ������ �ϼ��� ���
		if (n == 0) return color;
	}
	return 0;
}

int main() {

	// �Է¹ޱ�
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	// ��ü �ٵϾ� Ž��
	int ret = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// ���� �������� ���� ���
			if (arr[i][j] == 0) continue;

			ret = solve(i, j, arr[i][j]);
			if (ret != 0) break;
		}
		if (ret != 0) break;
	}

	if (ret == 0)cout << 0;
	else cout << ret << "\n" << ansI + 1 << " " << ansJ + 1;
}