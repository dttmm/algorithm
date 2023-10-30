#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 20

int N;
int M;
int arr[MAX_N][MAX_N];

// ���� üũ
bool isIn(int i, int j) {
	if (i >= 0 && i < N && j >= 0 && j < N)return true;
	return false;
}

// ��Ž
int solve() {
	int maxGold = 0;

	// ���� i, j ����
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// ���� k ����
			for (int k = 0; k <= (N - 1) * 2; k++) {
				int  cost = k * k + (k + 1) * (k + 1);

				// ���� i,j���� k��ŭ ������ �Ÿ����� ��ȭ ����
				int cnt = 0;
				for (int newI = i - k; newI <= i + k; newI++) {
					for (int newJ = j - k; newJ <= j + k; newJ++) {
						// ������ �� �ִ� ���� ��� ��� �о�
						if (abs(newI - i) + abs(newJ - j) > k) continue;
						// ���� ��� ��� �о�
						if (!isIn(newI, newJ)) continue;

						cnt += arr[newI][newJ];
					}
				}
				// �̵� ���
				int diff = (cnt * M) - cost;
				if (diff >= 0) maxGold = max(maxGold, cnt);
			}
		}
	}

	return maxGold;
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	int ret = solve();

	cout << ret;
}