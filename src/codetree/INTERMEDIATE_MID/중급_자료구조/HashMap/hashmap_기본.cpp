#include <iostream>
#include <unordered_map>

using namespace std;

unordered_map<int, int> m;

void add(int k, int v) {
	m[k] = v;
}

void remove(int k) {
	m.erase(k);
}

void find(int k) {
	if (m.find(k) != m.end()) {
		cout << m[k] << "\n";
	}
	else cout << "None" << "\n";
}

int main() {
	int N;
	cin >> N;

	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		if (s == "add") {
			int k, v;
			cin >> k >> v;

			add(k, v);
		}
		else if (s == "find") {
			int k;
			cin >> k;

			find(k);
		}
		else if (s == "remove") {
			int k;
			cin >> k;

			remove(k);
		}
	}
}