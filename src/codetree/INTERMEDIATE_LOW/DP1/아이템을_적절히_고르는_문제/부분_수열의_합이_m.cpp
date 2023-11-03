#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_M 10000
#define INF 1000000

int N;
int M;
int arr[MAX_N];
int d[MAX_M + 1];

// �ʱ�ȭ
void init() {
	for (int i = 1; i <= M; i++) {
		d[i] = INF;
	}
}

// dp
void solve() {
	// ���� �ϳ��� ���
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// �ش� ���Ҹ� �����Ͽ� m�� ������� �� �ּҰ� ����
		for (int m = M; m >= 1; m--) {
			if (n > m) continue;

			d[m] = min(d[m], d[m - n] + 1);
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

	if (d[M] == INF) d[M] = -1;
	cout << d[M];
}