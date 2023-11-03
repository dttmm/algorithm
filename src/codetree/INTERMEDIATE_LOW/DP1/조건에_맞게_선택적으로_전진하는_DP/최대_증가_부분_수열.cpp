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
	d[0] = 1;

	// ���� i���� ���� ���ʿ� �ִ� ���� �߿���
	// i���� ���� �͵� �߿��� dp�ִ밪 ã�Ƽ�
	for (int i = 1; i < N; i++) {

		int maxVal = 0;
		for (int j = 0; j < i; j++) {
			if (arr[j] >= arr[i]) continue;
			maxVal = max(maxVal, d[j]);
		}
		// i�� ���Խ�Ű�� �� ������Ʈ
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

	// dp�迭 �� �ִ밪 ã��
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}