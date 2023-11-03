#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int M;
int arr[MAX_N];
int d[MAX_M + 1];

// �ʱ�ȭ
void init() {
	for (int i = 1; i <= M; i++) {
		d[i] = INT_MAX;
	}
}

// dp
void solve() {
	// ���� m���� ���
	for (int m = 1; m <= M; m++) {
		// �������� ����Ͽ�
		for (int j = 0; j < N; j++) {
			int coin = arr[j];

			// ���� m���� �� coin�� �� ū ��� �о�
			if (coin > m) continue;
			// m-coin���� ���� �� ���� ��� �о�
			if (d[m - coin] == INT_MAX) continue;

			// �ּҰ� ����
			d[m] = min(d[m], d[m - coin] + 1);
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	init();

	solve();

	// ���� ���� ���
	if (d[M] == INT_MAX) d[M] = -1;
	cout << d[M];
}