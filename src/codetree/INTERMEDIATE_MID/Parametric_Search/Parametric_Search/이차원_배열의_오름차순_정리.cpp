#include <bits/stdc++.h>

using namespace std;

#define MIN(a, b) ((a) < (b) ? (a) : (b))

int N;
int K;

// ���� x�� k��° �̻�����
bool isPossible(int x) {
	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		cnt += MIN(x / i, N);
	}

	return cnt >= K;
}

int solve() {
	int s = 1;
	int e = 1000000000;
	int ans = -1;

	while (s <= e) {
		int mid = s + (e - s) / 2;

		// ���� x�� k��° �̻��� ��� -> �� ���� ���� k��° �̻����� Ȯ��
		if (isPossible(mid)) {
			ans = mid;
			e = mid - 1;
		}
		else {
			s = mid + 1;
		}
	}

	return ans;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> K;

	int ret = solve();

	cout << ret;
}