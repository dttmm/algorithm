#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int ans = INT_MAX;

void solve() {
	int minH = arr[0];		// �ּҰ�
	int maxH = arr[N - 1];	// �ִ밪

	// �ִ밪 �ּҰ� ����
	int diff = maxH - minH;
	if (diff <= 17) {
		ans = 0;
		return;
	}

	// ���� ���� 17�� ����
	int range = diff - 17;

	// �ִ� �ּ� ���̰� 17�̳��� �� �� �ֵ����ϴ�
	// ����(left ~ right)����
	// �ش� ������ �Ѿ�� ���̴� ���̸� ����
	for (int i = 0; i < range; i++) {
		int left = minH + i;
		int right = maxH - range + i;

		// �� ���
		int sum = 0;
		// left���� ���� ��� ���
		for (int j = 0; j < N; j++) {
			int n = arr[j];

			if (n < left) {
				int diff = left - n;
				sum += diff * diff;
			}
			else break;
		}

		// right���� ���� ��� ���
		for (int j = N - 1; j >= 0; j--) {
			int n = arr[j];

			if (n > right) {
				int diff = n - right;
				sum += diff * diff;
			}
			else break;
		}

		ans = min(ans, sum);
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ���� ������ ����
	sort(arr, arr + N);

	solve();

	cout << ans;
}