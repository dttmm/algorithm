#include <iostream>

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int d[MAX_N + 1];

// dp
void solve() {
	d[0] = 1;
	d[1] = 1;

	for (int i = 2; i <= N; i++) {
		d[i] = (d[i - 1] + 2 * d[i - 2]) % MOD;
	}
}

int main() {

	// ют╥б
	cin >> N;

	solve();

	cout << d[N];
}