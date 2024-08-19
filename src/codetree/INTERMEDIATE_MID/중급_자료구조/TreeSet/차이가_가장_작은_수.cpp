#include <iostream>
#include <set>

using namespace std;

#define INF 2'000'000'000

int N;
int M;
set<int> Set;

int main() {

	int min = INF;

	// ют╥б
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	for (int n : Set) {
		int target = n + M;
		auto it = Set.lower_bound(target);

		if (it != Set.end()) {
			int diff = *it - n;
			min = diff < min ? diff : min;
		}
	}

	if (min == INF) min = -1;
	cout << min;
}