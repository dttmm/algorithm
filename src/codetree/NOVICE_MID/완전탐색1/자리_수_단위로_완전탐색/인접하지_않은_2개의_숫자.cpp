#include <iostream>
#include <algorithm>

using namespace std;

#define R 2
#define MAX_N 100

int N;
int arr[MAX_N];
int tr[R];
int maxValue;

// ������������ 2�� ����
void solve(int k, int start) {
	if (k == R) {
		int sum = tr[0] + tr[1];

		maxValue = max(maxValue, sum);
	}
	else {
		for (int i = start; i < N; i++) {
			int n = arr[i];
			tr[k] = n;
			solve(k + 1, i + 2);
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