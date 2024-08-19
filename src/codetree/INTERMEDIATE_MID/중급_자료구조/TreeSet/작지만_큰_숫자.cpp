#include <iostream>
#include <set>

int N;
int M;
using namespace std;
set<int> Set;

int main() {

	// ÀÔ·Â
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		auto it = Set.upper_bound(n);
		if (it != Set.begin()) it--;
		else it = Set.end();

		if (it != Set.end()) {
			cout << *it << "\n";
			Set.erase(*it);
		}
		else cout << "-1\n";
	}
}