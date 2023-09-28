#include <iostream>
#include <climits>
#include<algorithm>
using namespace std;

#define R 3
#define N 6

int arr[N];
int total;	// ��ü �������� �� �ɷ��� ��
int ans = INT_MAX;

// ����
void solve(int k, int start, int sum) {
	// 3���� �̾��� ���
	if (k == R) {
		int sum2 = total - sum;
		int diff = abs(sum - sum2);
		ans = min(ans, diff);
	}
	else {
		for (int i = start; i < N; i++) {
			int n = arr[i];
			solve(k + 1, i + 1, sum + n);
		}
	}
}

int main() {

	// �Է� �ޱ�
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
		total += n;
	}

	// ����
	solve(0, 0, 0);

	cout << ans;
}