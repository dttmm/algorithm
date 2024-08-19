#include <bits/stdc++.h>

using namespace std;

#define MAX_N 50000

int N, K;
int arr[MAX_N];
int d[MAX_N];	// i���� ���� �� �ִ� �׷��� �ִ� ����
int L[MAX_N];	// 0~i���� �׷��� �ִ� ������ �ִ밪

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ����
	sort(arr, arr + N);

	// d�迭 ����
	int s = 0;
	int e = 0;
	while (e < N) {
		int diff = arr[e] - arr[s];
		if (diff > K) {
			s++;
		}
		else {
			d[e] = e - s + 1;
			e++;
		}
	}

	// L�迭 ����
	L[0] = d[0];
	for (int i = 1; i < N; i++) {
		L[i] = max(d[i], L[i - 1]);
	}

	// i���� ���� �� �ִ� �׷��� �ִ� ���� + �� ���� ���� �� �ִ� �׷��� �ִ� ����
	int ans = 0;
	for (int i = 0; i < N; i++) {
		if (i - d[i] >= 0) {
			ans = max(ans, d[i] + L[i - d[i]]);
		}
	}

	cout << ans;
}