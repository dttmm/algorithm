#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp
void solve() {
	// �ʱⰪ ����
	for (int k = 1; k <= arr[0]; k++) {
		d[k] = 1;
	}

	// ���� i�� ���
	for (int i = 1; i < N; i++) {
		// ù ��° ��ġ�������� �� ���� ��� �о�
		if (d[i] == 0) continue;

		// �ش� ��ġ���� ������ �� �ִ� ���� �����ϸ鼭 �ִ밪 ����
		int n = arr[i];
		for (int k = 1; k <= n; k++) {
			d[i + k] = max(d[i + k], d[i] + 1);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// �ִ밪 ã��
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}