#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 15
#define R 3

int N;
int M;
int arr[MAX_N][MAX_N];	// �� ǥ�� 1: ȭ��Ʈ, 2: ��
int cnt;

void solve(int k, int start_i, int start_j, int curColor) {
	// 3�� �������� ��
	if (k == R) {
		// �� ������ ������ ���
		if (start_i == N && start_j == M) cnt++;
	}
	else {
		for (int i = start_i; i < N; i++) {
			for (int j = start_j; j < M; j++) {
				// ���� ���� ���
				if (arr[i][j] * curColor > 0) continue;

				// �ּ��� �밢�� ��ĭ �Ʒ��� ����
				solve(k + 1, i + 1, j + 1, arr[i][j]);
			}
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			char c;
			cin >> c;
			if (c == 'W') arr[i][j] = 1;
			else arr[i][j] = -1;
		}
	}

	solve(0, 1, 1, arr[0][0]);

	cout << cnt;
}