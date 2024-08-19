#include <bits/stdc++.h>

using namespace std;

#define MAX_N 10000

int N;
int T;
int arr[MAX_N];
priority_queue<int> pq;

// 최대 k명으로 가능한지
bool isPossible(int k) {
	pq = {};
	int cur = 0;

	for (int i = 0; i < N; i++) {
		int n = arr[i];

		if (pq.size() < k) {
			pq.emplace(-1 * n);
			continue;
		}

		cur = -1 * pq.top(); pq.pop();

		pq.emplace(-1 * (cur + n));
	}

	while (!pq.empty()) {
		cur = -1 * pq.top(); pq.pop();
	}

	return cur <= T;
}

int solve() {
	int s = 1;
	int e = N;
	int ans = -1;

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

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N >> T;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ret = solve();

	cout << ret;
}