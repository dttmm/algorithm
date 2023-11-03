#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d[MAX_N];

// dp
void solve() {
	d[0] = 1;

	// ���� i�� ���
	for (int i = 2; i < N; i++) {

		// i���� ���ʿ� �ְ�
		// i���� ū ���� ã�Ƽ�
		// dp�迭 ������Ʈ
		int maxVal = 0;
		for (int j = 0; j < i; j++) {
			if (arr[j] <= arr[i]) continue;

			maxVal = max(maxVal, d[j]);
		}

		d[i] = maxVal + 1;
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}