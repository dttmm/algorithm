#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 20
#define R 3

int N;
int arr[MAX_N];
int tr[R];
int maxValue = -1;

// 3�� �̱�
void solve(int k, int start) {
	if (k == R) {
		int a = tr[0];
		int b = tr[1];
		int c = tr[2];
		int sum = a + b + c;

		// �� �ڸ��� ���鼭
		int n = 4;
		while (n > 0) {
			int restA = a % 10;
			int restB = b % 10;
			int restC = c % 10;

			// ĳ�� �߻� ���� �˻�
			if (restA + restB + restC >= 10) return;

			a /= 10;
			b /= 10;
			c /= 10;

			n--;
		}

		// ĳ�� �߻����� ���� ���
		maxValue = max(maxValue, sum);
	}
	else {
		for (int i = start; i < N; i++) {
			int n = arr[i];
			tr[k] = n;
			solve(k + 1, i + 1);
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve(0, 0);

	cout << maxValue;
}