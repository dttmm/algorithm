#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 500

int N;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];	// ���� ��ġ������ �ִ밪
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

// ���� üũ
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

// i, j ��ġ���� �ִ밪 ���ϱ�
int solve(int i, int j) {
	if (d[i][j] != 0) return d[i][j];

	// 4���� Ž���ϸ鼭 �� �� �ִ� �ִ밪 ����
	int maxVal = 0;
	for (int dir = 0; dir < 4; dir++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// ���� ����� ��� �о�
		if (!isIn(newI, newJ)) continue;
		// �� �� ���� ��� �о�
		if (arr[i][j] >= arr[newI][newJ])continue;

		int ret = solve(newI, newJ);
		maxVal = max(maxVal, ret);
	}

	// �ִ밪�� �ڽ� ���Խ��Ѽ� ����
	return d[i][j] = maxVal + 1;
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			solve(i, j);
		}
	}

	int maxVal = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			maxVal = max(maxVal, d[i][j]);
		}
	}

	cout << maxVal;
}