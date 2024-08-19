#include <iostream>
#include <map>
#include <string>
using namespace std;

int N;
map<string, int> Map;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		Map[s]++;
	}

	cout << fixed;
	cout.precision(4);

	for (auto& item : Map) {
		cout << item.first << " " << (double)item.second * 100 / N << "\n";
	}
}