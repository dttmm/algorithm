#include <iostream>
#include <set>

using namespace std;

int T;
int K;
set<int> Set;

int main() {

	// ют╥б
	cin >> T;
	for (int i = 0; i < T; i++) {
		cin >> K;
		Set.clear();

		for (int j = 0; j < K; j++) {
			char c;
			int x;
			cin >> c >> x;

			if (c == 'I') {
				Set.insert(x);
			}
			else {
				if (x == 1) {
					auto it = Set.rbegin();
					if (it != Set.rend()) Set.erase(*it);
				}
				else {
					auto it = Set.begin();
					if (it != Set.end()) Set.erase(*it);
				}
			}
		}

		if (Set.empty()) cout << "EMPTY\n";
		else cout << *Set.rbegin() << " " << *Set.begin() << "\n";
	}
}