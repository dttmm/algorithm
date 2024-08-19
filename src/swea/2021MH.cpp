#include<iostream>

using namespace std;

/*
* in 2021 out 11446
*/

#define MAX_N 100
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
long long M;
long long arr[MAX_N];

// 사탕 가방을 n개 만들수 있는지 여부
bool isP(long long n) {
	long long cnt = 0;

	for (int i = 0; i < N; i++) {
		cnt += arr[i] / n;

		if (cnt >= M) return true;
	}

	return false;
}

long long solve() {
	long long s = 1;
	long long e = static_cast<long long>(2e18);

	long long maxK = 0;
	while (s <= e) {
		long long mid = s + (e - s) / 2;

		if (isP(mid)) {
			s = mid + 1;
			maxK = MAX(maxK, mid);
		}
		else {
			e = mid - 1;
		}
	}
	return maxK;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> M;
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		long long ret = solve();

		cout << "#" << tc << " " << ret << "\n";
	}
}