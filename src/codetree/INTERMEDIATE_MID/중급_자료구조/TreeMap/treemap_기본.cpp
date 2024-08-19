#include <iostream>
#include <map>
#include <string>
using namespace std;

int N;
map<int, int> Map;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		if (s == "add") {
			int k, v;
			cin >> k >> v;

			Map[k] = v;
		}
		else if (s == "remove") {
			int k;
			cin >> k;

			Map.erase(k);
		}
		else if (s == "find") {
			int k;
			cin >> k;

			if (Map.find(k) != Map.end()) cout << Map[k] << "\n";
			else cout << "None\n";
		}
		else if (s == "print_list") {
			if (Map.size() == 0) cout << "None\n";
			else {
				for (auto& item : Map) {
					cout << item.second << " ";
				}
				cout << "\n";
			}
		}
	}
}