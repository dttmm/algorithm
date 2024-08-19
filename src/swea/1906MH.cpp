#include <iostream>

using namespace std;

/*
* in 1906 out 9780
*/

#define MAX_N 1000000
#define MAX(a,b) ((a) > (b) ? (a) : (b))

int N;
int arr[MAX_N + 1];
long long d[MAX_N + 1];

void solve() {
	d[1] = arr[1];
	for (int i = 2; i <= N; i++) {
		d[i] = MAX(d[i - 1], d[i - 2] + arr[i]);
	}
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;

	for (int tc = 1; tc <= T; tc++) {
		cin >> N;

		for (int i = 1; i <= N; i++) {
			cin >> arr[i];
		}

		solve();

		cout << "#" << tc << " " << d[N] << "\n";
	}
}