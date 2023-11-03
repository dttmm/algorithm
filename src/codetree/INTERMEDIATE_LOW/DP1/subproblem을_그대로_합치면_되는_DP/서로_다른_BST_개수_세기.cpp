#include <iostream>

using namespace std;

#define MAX_N 19

int N;
int d[MAX_N + 1];

// dp
void solve() {
	d[0] = 1;

	// ����� ������ i�϶�
	for (int i = 1; i <= N; i++) {
		int sum = 0;

		// 1���� i���� ��Ʈ��忡 ��ġ������ ��
		// ���ʿ� �� �� �ִ� ���� Ʈ�� ���� ������
		for (int root = 1; root <= i; root++) {
			// d[root - 1]: ������ ���� ����Ʈ��
			// d[i - root]: ������ ������ ����Ʈ��
			sum += d[root - 1] * d[i - root];
		}

		d[i] = sum;
	}
}

int main() {

	// �Է�
	cin >> N;

	solve();

	cout << d[N];
}