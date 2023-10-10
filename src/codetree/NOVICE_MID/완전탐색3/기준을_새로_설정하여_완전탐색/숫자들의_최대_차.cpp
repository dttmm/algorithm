#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1000

int N;
int K;
int arr[MAX_N];
int ans;

void solve(int start) {
	int maxVal = arr[start];
	int minVal = arr[start];	// start�ε����� �ּҰ����� ����

	int cnt = 0;
	for (int i = start; i < N; i++) {
		int n = arr[i];
		maxVal = max(maxVal, n);	// �ִ밪 ����

		// �ְ� �ּ� ���̰� K���� ũ�� -> ��
		if (maxVal - minVal > K) break;

		cnt++;
	}
	ans = max(ans, cnt);
}

int main() {

	// �Է�
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ���� ���� �켱 ����
	sort(arr, arr + N);

	// i�� �ּҰ����� ��� ��Ž
	for (int i = 0; i < N; i++) {
		solve(i);
	}

	cout << ans;
}