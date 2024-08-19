#include <iostream>
#include <set>

using namespace std;

int N;
set<int> Set;

int main() {

	int min = 2'000'000'000;
	Set.insert(0);

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);

		auto it = Set.upper_bound(n);
		if (it != Set.end()) {
			int diff = *it - *(--it);
			min = diff < min ? diff : min;
		}

		it = Set.lower_bound(n);
		int diff = *it - *(--it);
		min = diff < min ? diff : min;

		cout << min << "\n";
	}
}