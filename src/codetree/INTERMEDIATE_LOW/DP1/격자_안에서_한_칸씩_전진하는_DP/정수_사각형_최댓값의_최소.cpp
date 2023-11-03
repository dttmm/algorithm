#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];

void init() {
	// ���� ���� ����
	d[0][0] = arr[0][0];

	// ù �� ����
	for (int i = 1; i < N; i++) {
		d[i][0] = max(d[i - 1][0], arr[i][0]);
	}

	// ù �� ����
	for (int j = 1; j < N; j++) {
		d[0][j] = max(d[0][j - 1], arr[0][j]);
	}
}

// dp
void solve() {
	for (int i = 1; i < N; i++) {
		for (int j = 1; j < N; j++) {
			// ����, ���ʿ��� �ּҰ� ����
			int minVal = min(d[i - 1][j], d[i][j - 1]);
			// �ּҰ��� �ڽ� �� �ִ밪�� dp�迭 ������Ʈ
			d[i][j] = max(minVal, arr[i][j]);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	init();

	solve();

	cout << d[N - 1][N - 1];
}