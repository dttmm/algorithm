#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 100
#define MAX_X 100000

int N;
int arr[MAX_N];
bool d[MAX_X + 1];	// ���ڵ��� ���ļ� n�� ���� �� �ִ��� ����
int sum;	// ��� ���ڵ��� ��

// dp
void solve() {
	d[0] = true;

	// ���� �ϳ��� �̾Ƽ�
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// �� x�� ����� �ִ��� �˻�
		for (int x = sum; x >= n; x--) {
			if (d[x - n]) {
				d[x] = true;
			}
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		sum += arr[i];
	}

	solve();

	// �� i�� �����ϴ� �׷� A��
	// ������ �� sum-i�� �����ϴ� �׷찣�� ���� �ּҸ� ����
	int ans = MAX_X;
	for (int i = 1; i < sum; i++) {
		if (!d[i]) continue;

		int diff = i - (sum - i);
		ans = min(ans, abs(diff));
	}

	cout << ans;
}