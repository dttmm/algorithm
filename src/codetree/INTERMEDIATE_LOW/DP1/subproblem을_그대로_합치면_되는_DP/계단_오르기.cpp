#include <iostream>

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int d[MAX_N + 1];

// dp
void solve() {
	d[2] = 1;
	d[3] = 1;

	for (int i = 4; i <= N; i++) {
		// 2ĭ ���� + 3ĭ �������� ���� ���
		d[i] = (d[i - 2] + d[i - 3]) % MOD;
	}
}

int main() {

	// �Է�
	cin >> N;

	solve();

	cout << d[N];
}