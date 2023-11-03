#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int arr[] = { 1,2,5 };
int d[MAX_N + 1];

void solve() {
	// �ʱⰪ ����
	d[0] = 1;

	// n�� ����� ����
	for (int n = 1; n <= N; n++) {
		// 1,2,5�� ������� �� ����� �ִ� ����� �� ����
		for (int i = 0; i < 3; i++) {
			int m = arr[i];
			if (m > n) continue;

			d[n] = (d[n] + d[n - m]) % MOD;
		}
	}
}

int main() {

	// �Է�
	cin >> N;

	solve();

	cout << d[N];
}