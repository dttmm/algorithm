#include <iostream>
#include <unordered_set>

using namespace std;

int N, M;
unordered_set<int> Set;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	cin >> M;
	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;

		if (Set.find(n) != Set.end()) cout << "1\n";
		else cout << "0\n";
	}
}