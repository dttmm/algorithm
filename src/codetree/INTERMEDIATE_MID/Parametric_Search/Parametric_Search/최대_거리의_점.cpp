#include <bits/stdc++.h>
#include <algorithm>

using namespace std;

#define MAX_N 200000

int N;
int M;
int arr[MAX_N];

// 두 지점 사이의 거리를 x보다 크게했을 때, M-1개의 지점이 나올 수 있는지
bool isPossible(int x) {

	// 맨 왼쪽 지점은 고정
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

	// 고정한 맨 왼쪽지점 빼고 M-1개의 지점이 나올 수 있는지
	return cnt >= (M - 1);
}

int solve() {
	int s = 1;
	int e = 2000000000;
	int ans = -1;

	while (s <= e) {
		int mid = s + (e - s) / 2;

		// 두 지점 사이의 거리를 x보다 크게했을 때, M-1개의 지점이 나올 수 있으면 -> 가능할 수 있는 거리를 늘림
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

	// 좌표값이 오름차순으로 주어진다는 보장 없음
	sort(arr, arr + N);

	int ret = solve();

	cout << ret;
}