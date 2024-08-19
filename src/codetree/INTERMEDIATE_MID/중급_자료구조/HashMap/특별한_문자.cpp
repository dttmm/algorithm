#include <iostream>
#include <unordered_map>
#include <algorithm>
#include <string>
using namespace std;

string s;
unordered_map<char, int> map;

int main() {

	// ют╥б
	cin >> s;
	for (int i = 0; i < s.length(); i++) {
		char c = s[i];

		map[c]++;
	}

	for (int i = 0; i < s.length(); i++) {
		char c = s[i];

		if (map[c] == 1) {
			cout << c;
			return 0;
		}
	}

	cout << "None";
}