#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int arr[MAX_N + 1];
int d[MAX_M + 1];

struct Node {
	int n;
	int v;
}nodes[MAX_N];

// dp
void solve() {
	// ���̰� n�� ���븦 ����Ͽ��� ��
	for (int n = 1; n <= N; n++) {
		int v = arr[n];

		// ���̰� m�� ���븦 ����� ���� �ִ밪 ã��
		for (int m = 1; m <= N; m++) {
			if (n > m) continue;

			d[m] = max(d[m], d[m - n] + v);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << d[N];
}