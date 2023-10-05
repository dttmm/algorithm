#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 20
#define MAX_X 10

int N;
bool xyrr[MAX_X + 1][MAX_X + 1];
int ans;

// X�࿡ ������ �� �׸���
void drawX(bool arr[][MAX_X + 1], int n) {
	for (int i = 0; i <= MAX_X; i++) {
		*(*(arr + n) + i) = false;
	}
}

// Y�࿡ ������ �� �׸���
void drawY(bool arr[][MAX_X + 1], int n) {
	for (int i = 0; i <= MAX_X; i++) {
		*(*(arr + i) + n) = false;
	}
}

// ��Ž
void solve(int k, bool arr[][MAX_X + 1]) {
	// �� 3���� �� �׸� ���
	if (k == 3) {
		// ��� ���� ���� �� �ִ��� �˻�
		for (int i = 0; i <= MAX_X; i++) {
			for (int j = 0; j <= MAX_X; j++) {
				if (arr[i][j] == true) return;
			}
		}
		ans = 1;
	}
	else {
		for (int i = 0; i <= MAX_X; i++) {
			bool brr[MAX_X + 1][MAX_X + 1] = {};
			copy(&arr[0][0], &arr[0][0] + (MAX_X + 1) * (MAX_X + 1), &brr[0][0]);

			// X�� �׸���
			drawX(brr, i);
			// ���� ���� �׸���
			solve(k + 1, brr);

			bool crr[MAX_X + 1][MAX_X + 1] = {};
			copy(&arr[0][0], &arr[0][0] + (MAX_X + 1) * (MAX_X + 1), &crr[0][0]);

			// Y�� �׸���
			drawY(crr, i);
			// ���� ���� �׸���
			solve(k + 1, crr);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;
		xyrr[x][y] = true;
	}

	solve(0, xyrr);

	cout << ans;
}