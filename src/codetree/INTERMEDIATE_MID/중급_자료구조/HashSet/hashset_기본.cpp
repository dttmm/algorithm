#include <iostream>
#include <unordered_set>
#include <string>
using namespace std;

int N;
unordered_set<int> Set;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		int x;
		cin >> s >> x;

		if (s == "add") {
			Set.insert(x);
		}
		else if (s == "remove") {
			Set.erase(x);
		}
		else if (s == "find") {
			if (Set.find(x) != Set.end()) cout << "true" << "\n";
			else cout << "false" << "\n";
		}
	}
}