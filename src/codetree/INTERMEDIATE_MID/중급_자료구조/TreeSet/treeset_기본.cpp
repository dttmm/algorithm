#include <iostream>
#include <set>
#include <string>

using namespace std;

int N;
set<int> Set;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		int x;
		if (s == "add") {
			cin >> x;

			Set.insert(x);
		}
		else if (s == "remove") {
			cin >> x;

			Set.erase(x);
		}
		else if (s == "find") {
			cin >> x;

			if (Set.find(x) != Set.end()) cout << "true\n";
			else cout << "false\n";
		}
		else if (s == "lower_bound") {
			cin >> x;

			auto it = Set.lower_bound(x);
			if (it != Set.end()) cout << *it << "\n";
			else cout << "None\n";
		}
		else if (s == "upper_bound") {
			cin >> x;

			auto it = Set.upper_bound(x);
			if (it != Set.end()) cout << *it << "\n";
			else cout << "None\n";
		}
		else if (s == "largest") {
			auto it = Set.rbegin();
			if (it != Set.rend()) cout << *it << "\n";
			else cout << "None\n";
		}
		else if (s == "smallest") {
			auto it = Set.begin();
			if (it != Set.end()) cout << *it << "\n";
			else cout << "None\n";
		}
	}
}