#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 1000000
#define R 4
#define N 6

int arr[N];
int tr[R];
bool visited[N];
int ans = MAX_X * 10;
int total;	// ������ �ɷ� ����

// ������ 4�� �̱�
// �������� 4�� �������� �ߴµ� �ڿ������� �̾ƹ����� ���ʸ� ���� ���� ���� ����� �� �� �� �� ����
void solve(int k) {
	if (k == R) {
		int sum1 = tr[0] + tr[1];	 // �� ���� ��
		int sum2 = tr[2] + tr[3];	// �� ���� ��
		int sum3 = total - sum1 - sum2;	// ������ �� ��

		int maxVal = max(sum1, sum2);
		maxVal = max(maxVal, sum3);

		int minVal = min(sum1, sum2);
		minVal = min(minVal, sum3);

		int diff = maxVal - minVal;
		ans = min(ans, diff);
	}
	else {
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			int n = arr[i];
			tr[k] = n;
			solve(k + 1);
			visited[i] = false;
		}
	}
}

int main() {

	// �Է� �ޱ�
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		total += arr[i];
	}

	// ����
	solve(0);

	cout << ans;
}