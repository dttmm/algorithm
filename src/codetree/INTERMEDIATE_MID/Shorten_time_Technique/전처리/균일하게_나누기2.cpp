#include <iostream>

using namespace std;

/*
* ���� 25�� ���� 8��
*/

#define MAX_N 1000
#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define MIN(a, b) ((a) < (b) ? (a) : (b))

int N;
int arr[MAX_N + 1][MAX_N + 1];	// ���� �ִ� ��ġ ǥ��
int d[MAX_N + 1][MAX_N + 1];	// (0,0) ~ (x,y) ������ �� �� ����

// ���� �� ���� ���� -> ������ �̿�
void setD() {
	for (int x = 1; x <= MAX_N; x++) {
		for (int y = 1; y <= MAX_N; y++) {
			d[x][y] = d[x - 1][y] + d[x][y - 1] - d[x - 1][y - 1] + arr[x][y];
		}
	}
}

// M�� �ּҰ� ��ȯ
int solve() {
	int ans = MAX_N;

	for (int x = 2; x < MAX_N; x += 2) {
		for (int y = 2; y < MAX_N; y += 2) {
			// 1��и鿡 �ִ� ���� ����
			int cnt1 = d[MAX_N][MAX_N] - d[x][MAX_N] - d[MAX_N][y] + d[x][y];
			int maxCnt = cnt1;

			// 2��и鿡 �ִ� ���� ����
			int cnt2 = d[x][MAX_N] - d[x][y];
			maxCnt = MAX(maxCnt, cnt2);

			// 3��и鿡 �ִ� ���� ����
			int cnt3 = d[x][y];
			maxCnt = MAX(maxCnt, cnt3);

			// 4��и鿡 �ִ� ���� ����
			int cnt4 = d[MAX_N][y] - d[x][y];
			maxCnt = MAX(maxCnt, cnt4);

			// M�� �ּҰ� ������Ʈ
			ans = MIN(ans, maxCnt);
		}
	}
	return ans;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;

		arr[x][y] = 1;
	}

	setD();

	int ret = solve();

	cout << ret;
}