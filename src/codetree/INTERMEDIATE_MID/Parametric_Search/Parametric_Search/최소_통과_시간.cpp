#include <bits/stdc++.h>

using namespace std;

#define MAX_M 100000

int N;
int M;
int arr[MAX_M];

// 한 통로당 x초 이하가 되도록 가능한지
bool isPossible(long long x) {
	long long cnt = 0;

	for (int i = 0; i < M; i++) {
		int n = arr[i];
		cnt += x / n;

		if (cnt >= N) break;
	}

	return cnt >= N;
}

long long solve() {
	long long s = 1;
	long long e = 200000000000000;
	long long ans = -1;

	while (s <= e) {
		long long mid = s + (e - s) / 2;
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

	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		cin >> arr[i];
	}

	long long ret = solve();

	cout << ret;
}