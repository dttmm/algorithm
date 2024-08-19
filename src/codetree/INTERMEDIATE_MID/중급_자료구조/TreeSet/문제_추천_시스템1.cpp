#include <iostream>
#include <set>
#include <string>

using namespace std;

int N;
int M;
set<pair<int, int> > Set;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		int P, L;
		cin >> P >> L;

		Set.insert(make_pair(L, P));
	}

	cin >> M;
	for (int i = 0; i < M; i++) {
		string s;
		cin >> s;

		if (s == "ad") {
			int P, L;
			cin >> P >> L;

			Set.insert(make_pair(L, P));
		}
		else if (s == "sv") {
			int P, L;
			cin >> P >> L;

			Set.erase(make_pair(L, P));
		}
		else {
			int n;
			cin >> n;

			if (n == 1) {
				auto it = Set.rbegin();
				cout << (*it).second << "\n";
			}
			else {
				auto it = Set.begin();
				cout << (*it).second << "\n";
			}
		}
	}
}