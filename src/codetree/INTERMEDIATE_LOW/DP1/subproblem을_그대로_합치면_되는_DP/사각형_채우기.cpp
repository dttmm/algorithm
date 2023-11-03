#include <iostream>

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int d[MAX_N + 1];

// dp
void solve() {
	d[1] = 1;
	d[2] = 2;

	// '��' ����� �ٷ� ���� �簢�� �ڿ� �߰��� �� �ְ�
	// '��' ����� �ι�° �� �簢�� �ڿ� �߰��� �� ����
	for (int i = 3; i <= N; i++) {
		d[i] = (d[i - 1] + d[i - 2]) % MOD;
	}
}

int main() {

	// �Է�
	cin >> N;

	solve();

	cout << d[N];
}