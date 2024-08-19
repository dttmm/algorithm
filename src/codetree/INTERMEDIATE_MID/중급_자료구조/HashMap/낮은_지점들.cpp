#include <iostream>
#include <unordered_map>
using namespace std;

int N;
unordered_map<int, long long> map;

int main() {

	// ют╥б
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		long long y;
		cin >> x >> y;

		if (map.find(x) != map.end()) {
			if (y < map[x]) map[x] = y;
		}
		else map[x] = y;
	}

	long long sum = 0;
	for (auto& item : map) {
		sum += item.second;
	}

	cout << sum;
}