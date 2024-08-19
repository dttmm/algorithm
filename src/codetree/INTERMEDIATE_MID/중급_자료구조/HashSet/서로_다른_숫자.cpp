#include <iostream>
#include <unordered_set>

using namespace std;

int N;
unordered_set<int> Set;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	cout << Set.size();
}