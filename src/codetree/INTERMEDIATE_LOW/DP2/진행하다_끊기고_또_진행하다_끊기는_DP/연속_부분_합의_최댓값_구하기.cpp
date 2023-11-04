#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];
int d[MAX_N];	// i�� ������ ������ �� �ִ밪

void solve() {
	d[0] = arr[0];

	// ���� ������� �ڽ��� ������ ���ҷ� �߰��ϰų�
	// �ڽź��� �����ϰų� �� �� �ִ밪 ��
	for (int i = 1; i < N; i++) {
		d[i] = max(d[i - 1] + arr[i], arr[i]);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	int ans = -10000;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}