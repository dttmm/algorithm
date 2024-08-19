#include <iostream>
#include <map>
using namespace std;

int N;
map<int, int> Map;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		if (Map.find(n) == Map.end()) Map[n] = i;
	}

	for (auto& item : Map) {
		cout << item.first << " " << item.second << "\n";
	}
}