#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 100

int N;
int arr[MAX_X + 1];	// x��ǥ�� � ���ĺ��� �ִ��� 1:G, 2:H

int main() {

	// �Է� �ޱ�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		char c;
		cin >> x >> c;

		if (c == 'G') arr[x] = 1;
		else arr[x] = 2;
	}

	// G�� H ���� � ���Դ��� ī��Ʈ 1:G, 2:H
	int cnt[3] = {};
	int ans = 0;
	// ���� ���� i��������
	for (int i = 0; i <= MAX_X; i++) {
		int n = arr[i];
		if (n == 0) continue;

		fill(cnt, cnt + 3, 0);
		cnt[n]++;

		// j�������� G�� H���� cnt�� ������
		for (int j = i + 1; j <= MAX_X; j++) {
			int m = arr[j];
			if (m == 0) continue;;

			cnt[m]++;

			int diff = j - i;
			// G�� H�� ���� ���� ���
			if (cnt[1] == cnt[2]) ans = max(ans, diff);
			// H�� ���� ���
			else if (cnt[1] == 0 && cnt[2] != 0)ans = max(ans, diff);
			// G�� ���� ���
			else if (cnt[1] != 0 && cnt[2] == 0)ans = max(ans, diff);
		}
	}

	cout << ans;
}