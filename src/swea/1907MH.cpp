#include <iostream>

using namespace std;

/*
* in 1907 out 9843
*/

#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define MAX_X 2e9

long long N;

long long solve(long long s, long long e) {

	while (s <= e) {

		long long mid = s + (e - s) / 2;

		long long amount = mid * (mid + 1) / 2;

		if (amount == N) {
			return mid;
		}
		else if (amount < N) {
			s = mid + 1;
		}
		else {
			e = mid - 1;
		}
	}

	return -1;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		cin >> N;

		long long ret = solve(1, MAX_X);

		cout << "#" << tc << " " << ret << "\n";
	}
}
