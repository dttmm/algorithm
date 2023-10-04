#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_X 40000
#define MAX_N 100

int N;
int ans = INT_MAX;
int xArr[MAX_N + 1];
int yArr[MAX_N + 1];

// �� �ϳ��� �����غ��鼭 ��Ž
void solve() {
	// i: ������ ��
	for (int i = 0; i < N; i++) {
		int maxX = 0;
		int minX = INT_MAX;
		int maxY = 0;
		int minY = INT_MAX;
		// ��� �� ��ȸ
		for (int j = 0; j < N; j++) {
			// ������ �� ����
			if (i == j) continue;

			int x = xArr[j];
			int y = yArr[j];

			maxX = max(maxX, x);
			minX = min(minX, x);

			maxY = max(maxY, y);
			minY = min(minY, y);
		}
		int area = (maxX - minX) * (maxY - minY);
		ans = min(ans, area);
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N;

	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;

		xArr[i] = x;
		yArr[i] = y;
	}

	// ��Ž
	solve();

	cout << ans;
}