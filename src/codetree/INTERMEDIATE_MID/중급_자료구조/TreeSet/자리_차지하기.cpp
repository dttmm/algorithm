#include <iostream>
#include <set>

using namespace std;

int N;
int M;
set<int> Set;

void init() {
	for (int i = 1; i <= M; i++) {
		Set.insert(-1 * i);
	}
}

int main() {

	// ют╥б
	cin >> N >> M;

	init();

	int ans = 0;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;
		n *= -1;

		auto it = Set.lower_bound(n);
		if (it != Set.end()) {
			Set.erase(*it);
			ans++;
		}
		else break;
	}

	cout << ans;
}