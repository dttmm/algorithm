#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 50
#define MAX_D 1000

int N;
int M;
int D;
int S;
int arr[MAX_N + 1][MAX_N + 1];	// i�� ġ� j�� ����� value�ʿ� ����
int brr[MAX_N + 1];				// i�� ����� value�ʿ� ����
int ans;

// i�� ġ�� ������� �� ����
int getCnt(int i) {
	int cnt = 0;
	for (int j = 1; j <= N; j++) {
		if (arr[i][j] > 0) cnt++;
	}
	return cnt;
}

void solve() {
	// i: ġ��
	for (int i = 1; i <= M; i++) {
		bool flag = true;	// ���� ���ɼ� �÷���. true: ���� ���ɼ� ����

		// j: i�� ġ� value�ʿ� ���� ���
		for (int j = 1; j <= N; j++) {
			// ���� ��� ���� ��� �о�
			if (brr[j] == 0) continue;

			// �ش� ġ� �ȸԾ��µ� ���� ����� �ִ� ��� -> i�� ġ��� ������ ����
			if (arr[i][j] == 0) {
				flag = false;
				break;
			}

			// ���� �ð��� ���� �ð����� �� �̸� ��� -> i�� ġ��� ������ ����
			if (brr[j] <= arr[i][j]) {
				flag = false;
				break;
			}
		}

		// ���� ����� �����ϴ� ��� -> ���� ġ� �� �� �ִ� �ĺ�
		if (flag) {
			// �ش� ġ� ���� ��� ����
			int ret = getCnt(i);
			ans = max(ans, ret);
		}
	}
}

int main() {

	// �Է� �ޱ�
	cin >> N >> M >> D >> S;
	for (int i = 0; i < D; i++) {
		int n;
		int c;
		int t;
		cin >> n >> c >> t;

		if (arr[c][n] == 0) arr[c][n] = t;
		else arr[c][n] = min(arr[c][n], t);

	}

	for (int i = 0; i < S; i++) {
		int n;
		int t;
		cin >> n >> t;

		brr[n] = t;
	}

	solve();

	cout << ans;
}