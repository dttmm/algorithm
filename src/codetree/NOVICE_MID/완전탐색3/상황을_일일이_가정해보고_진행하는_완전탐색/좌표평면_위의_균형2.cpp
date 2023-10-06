#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 200

int N;
int xrr[MAX_N];
int yrr[MAX_N];
int ans = 100;

void solve() {
	// Ȧ�� ���� ���� �߱�
	for (int i = 1; i < MAX_X; i++) {
		if (i % 2 == 0) continue;
		for (int j = 1; j < MAX_X; j++) {
			if (j % 2 == 0) continue;

			// ���� ������ �������� ��и� ����
			int area1 = 0;	// 1��и鿡 �ִ� ���� ����
			int area2 = 0;	// 2��и鿡 �ִ� ���� ����
			int area3 = 0;	// 3��и鿡 �ִ� ���� ����
			int area4 = 0;	// 4��и鿡 �ִ� ���� ����

			// �� ��и鿡 ���� �� ������ ����
			for (int k = 0; k < N; k++) {
				int x = xrr[k];
				int y = yrr[k];

				if (x > i && y > j) area1++;
				else if (x < i && y > j) area2++;
				else if (x < i && y < j) area3++;
				else if (x > i && y < j) area4++;
			}

			// ���� ���� ���� ���� �ִ� ��и� ���ϱ�
			int maxVal = max(area1, area2);
			maxVal = max(maxVal, area3);
			maxVal = max(maxVal, area4);

			ans = min(ans, maxVal);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;

		// ��ǥ�� 2�� �÷���
		xrr[i] = 2 * x;
		yrr[i] = 2 * y;
	}

	solve();

	cout << ans;
}