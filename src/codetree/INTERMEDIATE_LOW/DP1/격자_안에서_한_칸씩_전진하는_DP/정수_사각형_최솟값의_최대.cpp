#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];

// i, j���� ������ ���� ��ο����� �ּҰ��� �ִ밪 ��ȯ
int solve(int i, int j) {
	if (d[i][j] != 0) return d[i][j];

	int maxVal = 0;

	// �Ʒ������� �� �� �ִ� ���
	if (i + 1 < N) {
		int ret = solve(i + 1, j);
		maxVal = max(maxVal, ret);
	}

	// ���������� �� �� �ִ� ���
	if (j + 1 < N) {
		int ret = solve(i, j + 1);
		maxVal = max(maxVal, ret);
	}

	// �ڱ� �ڽ��� �����Ͽ� dp�迭 ������Ʈ
	if (maxVal != 0)d[i][j] = min(maxVal, arr[i][j]);
	else d[i][j] = arr[i][j];

	return d[i][j];
}

int main() {

	// �Է�
	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	solve(0, 0);

	cout << d[0][0];
}