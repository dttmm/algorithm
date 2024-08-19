#include <iostream>
#include <unordered_map>
using namespace std;

int N;
unordered_map<string, int> m;

int main() {
	cin >> N;
	int maxVal = 0;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		m[s]++;
		maxVal = m[s] > maxVal ? m[s] : maxVal;
	}

	cout << maxVal;
}