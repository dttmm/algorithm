#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int ans = 1000000;

// ���ڸ� �����ϰ� ������ ���ڰ��� ���� �� �ּ� ã��
void solve() {
	// ������ ���� ����
	for (int i = 0; i < N; i++) {
		int brr[MAX_N] = {};
		int index = 0;

		// ������ ���� brr�� ����
		for (int j = 0; j < N; j++) {
			if (i == j) continue;
			brr[index++] = arr[j];
		}

		// ������ ���ڻ��� ���� �� ���ϱ�
		int sum = 0;
		for (int i = 1; i < N - 1; i++) {
			int diff = abs(brr[i] - brr[i - 1]);
			sum += diff;
		}

		ans = min(ans, sum);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 2���� ���� ����
	for (int i = 0; i < N; i++) {
		arr[i] *= 2;
		solve();
		arr[i] /= 2;
	}

	cout << ans;
}