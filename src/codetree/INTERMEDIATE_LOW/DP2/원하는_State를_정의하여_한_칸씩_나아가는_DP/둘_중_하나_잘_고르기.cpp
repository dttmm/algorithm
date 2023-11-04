#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define INF -1000000

int N;
int red[2 * MAX_N + 1];
int blue[2 * MAX_N + 1];
int d[2 * MAX_N + 1][MAX_N + 1];	// n��° ���ÿ� �������� ������ Ƚ���� i�϶� �ִ밪

// dp
void solve() {
	// dp�迭 �ʱ�ȭ <- �ִ밪 ���ҰŴϱ� ���� ���� ������ ��ȯ
	for (int n = 0; n <= 2 * N; n++) {
		for (int i = 0; i <= N; i++) {
			d[n][i] = INF;
		}
	}
	// 0��°�� �������� �������� ������ 0����
	d[0][0] = 0;

	// n��°��
	for (int n = 1; n <= 2 * N; n++) {
		// �������� i�� ������ ���
		for (int i = 0; i <= N; i++) {
			// �Ķ����� ������ Ƚ���� n-i���� ��
			// �Ķ����� ������ Ƚ���� N�� �Ѿ�� �ȵ�
			if (n - i > N) continue;

			// ���� ������� �Ķ����� �����ϴ� ���
			d[n][i] = max(d[n][i], d[n - 1][i] + blue[n]);

			if (i - 1 >= 0) {
				// ���� ������� �������� �����ϴ� ���
				d[n][i] = max(d[n][i], d[n - 1][i - 1] + red[n]);
			}
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 1; i <= 2 * N; i++) {
		int r, b;
		cin >> r >> b;

		red[i] = r;
		blue[i] = b;
	}

	solve();

	// ������������ �ִ밪 ã��
	int ans = 0;
	for (int i = 0; i <= N; i++) {
		ans = max(ans, d[2 * N][i]);
	}

	cout << ans;
}