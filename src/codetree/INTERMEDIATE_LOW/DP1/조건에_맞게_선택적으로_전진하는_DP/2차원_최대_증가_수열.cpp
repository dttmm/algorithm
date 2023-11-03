#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 50

int N;
int M;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];

// dp
void solve() {
	// �ʱ�ȭ
	d[0][0] = 1;

	// ���� i, j�� ��Ƽ�
	for (int i = 1; i < N; i++) {
		for (int j = 1; j < M; j++) {
			// i,j ���� ���� ��ܿ� �ִ� ������ ��
			// i,j�� �� �� �ִ� ��츦 ã�Ƽ�
			// ���� �ִ밪 ������Ʈ
			for (int ii = 0; ii < i; ii++) {
				for (int jj = 0; jj < j; jj++) {
					// ���������� 1,1���� �� �� ���� ��� �о�
					if (d[ii][jj] == 0) continue;
					// ���� ������ ū ���� ���� �ʴ� ��� �о�
					if (arr[ii][jj] >= arr[i][j]) continue;

					d[i][j] = max(d[i][j], d[ii][jj] + 1);
				}
			}
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}

	solve();

	int ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			ans = max(ans, d[i][j]);
		}
	}

	cout << ans;
}