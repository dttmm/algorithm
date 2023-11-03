#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define INF 100000000

int N;
int arr[MAX_N + 1][MAX_N + 1];
int d[MAX_N + 1][MAX_N + 1];

// dp
void solve() {
	// ù��, ���� �ʱ�ȭ
	for (int i = 0; i < N; i++) {
		d[0][i] = INF;
		d[i + 1][N] = INF;
	}
	// ���� ���� �ٷ� �� �ʱ�ȭ <- ���� �������� �ּҰ��� �ڱ� �ڽ��� �ǾߵǱ� ����
	d[0][N - 1] = 0;

	for (int i = 1; i <= N; i++) {
		for (int j = N - 1; j >= 0; j--) {
			// ���ʰ� �����ʿ����� ������������ �� �� ����
			d[i][j] = min(d[i - 1][j], d[i][j + 1]) + arr[i][j];
		}
	}
}

int main() {

	// �Է�
	cin >> N;

	for (int i = 1; i <= N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	solve();

	cout << d[N][0];
}