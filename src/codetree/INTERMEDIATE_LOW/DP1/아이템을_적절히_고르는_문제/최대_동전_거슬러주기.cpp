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
		d[i] = -1;
	}
}

// dp
void solve() {
	// m���� ����� ����
	for (int m = 1; m <= M; m++) {
		// �������� �� ����غ��鼭 �ִ밪 ������Ʈ
		for (int i = 0; i < N; i++) {
			int n = arr[i];
			if (n > m) continue;
			if (d[m - n] == -1) continue;

			d[m] = max(d[m], d[m - n] + 1);
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

	cout << d[M];
}