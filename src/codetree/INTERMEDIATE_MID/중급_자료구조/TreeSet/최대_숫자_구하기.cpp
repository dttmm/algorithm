#include <iostream>
#include <set>

using namespace std;

int N;
int M;
set<int> Set;

int main() {

	// ют╥б
	cin >> N >> M;

	for (int i = 1; i <= M; i++) {
		Set.insert(i);
	}

	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.erase(n);
		cout << *Set.rbegin() << "\n";
	}
}