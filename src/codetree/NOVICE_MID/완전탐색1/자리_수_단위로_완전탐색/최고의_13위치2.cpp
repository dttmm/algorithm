#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 20

int N;
int arr[MAX_N][MAX_N];
int ans;

// [i][j-2] ~ [i][j] ���� �� ����
int getSum(int i, int j) {
	return arr[i][j] + arr[i][j - 1] + arr[i][j - 2];
}

// �ι�° ���� ����
void solve(int index, int sum) {
	for (int x = index; x < N * N; x++) {
		int i = x / N;
		int j = x % N;

		if (j < 2) continue;

		int total = sum + getSum(i, j);
		ans = max(ans, total);
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	// ù��° ���� ����
	for (int i = 0; i < N; i++) {
		for (int j = 2; j < N; j++) {
			int index = i * N + j;
			int sum = getSum(i, j);

			// �ι�° ���� ����
			solve(index + 3, sum);
		}
	}

	cout << ans;
}