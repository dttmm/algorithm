#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int B;
int arr[MAX_N];
int ans;

void solve() {
	// �ݰ� ������ �л� I�ϳ� ����
	for (int i = 0; i < N; i++) {
		int total = 0;	// �� ���� �ݾ�
		int cnt = 0;	// ���� ���� �ο�

		// ���� �� �������� ����
		for (int j = 0; j < N; j++) {
			total += arr[j];

			// �ݰ� ���� ���
			if (i == j) total -= arr[j] * 0.5;

			// ���� �Ѿ�� ���
			if (total > B) break;

			cnt++;
			ans = max(ans, cnt);
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N >> B;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ���������� ����
	sort(arr, arr + N);

	solve();

	cout << ans;
}