#include <iostream>
#include <set>

using namespace std;

int N;
int K;
set<int> Set;

int main() {

	// ют╥б
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	auto it = Set.rbegin();
	for (int i = 0; i < K; i++) {
		cout << *it << " ";
		it++;
	}
}