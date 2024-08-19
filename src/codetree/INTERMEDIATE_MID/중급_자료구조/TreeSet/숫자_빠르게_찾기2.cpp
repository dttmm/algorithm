#include <iostream>
#include <set>

using namespace std;

int N;
int M;
set<int> Set;

int main() {

	// ют╥б
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		auto it = Set.lower_bound(n);
		if (it != Set.end()) cout << *it << "\n";
		else cout << "-1\n";
	}
}