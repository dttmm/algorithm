#include <bits/stdc++.h>

using namespace std;

int N;

// x가 N번째 이상 숫자인지
bool isPossible(int x) {
	int n = x - x / 3 - x / 5 + x / 15;
	return n >= N;
}

int solve() {
	int s = 1;
	int e = 2000000000;
	int ans = 0;

	while (s <= e) {
		int mid = s + (e - s) / 2;

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
	cin >> N;

	int ret = solve();

	cout << ret;
}