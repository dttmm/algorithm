#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int K;
int arr[MAX_N];
int ans = 100;

bool solve(int maxVal) {
	int prev = 0;	// ���� �� �ε���

	// �� ��ȸ
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// �ִ밪���� �� ū ���̸� �о�
		if (n > maxVal) continue;

		// K�̳��� �� �� ������ maxVal�� ������ �ƴ�
		if (i - prev > K) return false;

		prev = i;
	}

	return true;
}

int main() {

	// �Է�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	for (int i = 0; i < N; i++) {
		int n = arr[i]; // �ִ밪���� ������ ��
		if (n < arr[0]) continue; // ������ ���ٴ� Ŀ�ߵ�
		if (n < arr[N - 1]) continue; // ������ ���ٵ� Ŀ�ߵ�

		bool ret = solve(n);

		if (ret) ans = min(ans, n);
	}

	cout << ans;
}