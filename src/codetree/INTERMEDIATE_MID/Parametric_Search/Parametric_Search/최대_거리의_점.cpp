#include <bits/stdc++.h>
#include <algorithm>

using namespace std;

#define MAX_N 200000

int N;
int M;
int arr[MAX_N];

// �� ���� ������ �Ÿ��� x���� ũ������ ��, M-1���� ������ ���� �� �ִ���
bool isPossible(int x) {

	// �� ���� ������ ����
	int left = arr[0];
	int cnt = 0;

	for (int i = 1; i < N; i++) {
		int right = arr[i];
		int diff = right - left;

		while (diff < x) {
			i++;
			if (i == N) return false;

			right = arr[i];
			diff = right - left;
		}

		cnt++;
		if (cnt == M - 1) break;
		left = arr[i];
	}

	// ������ �� �������� ���� M-1���� ������ ���� �� �ִ���
	return cnt >= (M - 1);
}

int solve() {
	int s = 1;
	int e = 2000000000;
	int ans = -1;

	while (s <= e) {
		int mid = s + (e - s) / 2;

		// �� ���� ������ �Ÿ��� x���� ũ������ ��, M-1���� ������ ���� �� ������ -> ������ �� �ִ� �Ÿ��� �ø�
		if (isPossible(mid)) {
			ans = mid;
			s = mid + 1;
		}
		else {
			e = mid - 1;
		}
	}

	return ans;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// ��ǥ���� ������������ �־����ٴ� ���� ����
	sort(arr, arr + N);

	int ret = solve();

	cout << ret;
}