#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_H 1000

int N;
int arr[MAX_N];
int ans;

// ��Ž
void solve() {
	// ������ ���� ���鼭
	for (int h = 1; h < MAX_H; h++) {
		int cnt = 0;	// ���� ��� ����
		bool flag = false;	// ������ �����̾������� �÷���

		// ������� ��ȸ
		for (int j = 0; j < N; j++) {
			int n = arr[j] - h;

			// ������ ���̰� �ؼ��麸�� ���� ���
			if (n > 0)
				flag = true;
			// ������ ���̰� �ؼ��麸�� ���� ���
			else {
				// ������ �����̾��� ���
				if (flag) cnt++;
				flag = false;
			}
		}

		// ������ ���� ó��
		if (flag) cnt++;

		ans = max(ans, cnt);

		if (cnt == 0) break;
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << ans;
}