#include<iostream>

using namespace std;

#define MAX(a, b) ((a) > (b) ? (a) : (b));
#define MAX_S 2e9

long long S;

// n까지의 합이 S이하인지 여부 리턴
bool f(long long n) {
	if (n * (n + 1) / 2 <= S) return true;
	return false;
}

long long solve() {
	long long s = 1;
	long long e = MAX_S;

	long long max_n = 0;
	while (s <= e) {
		long long mid = s + ((e - s) / 2);

		if (f(mid)) {
			s = mid + 1;
			max_n = MAX(max_n, mid);
		}
		else {
			e = mid - 1;
		}
	}

	return max_n;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> S;

	cout << solve();
}