#include <iostream>
#include <set>

using namespace std;

int N;
int M;
set<pair<int, int> > Set;

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;

		Set.insert(make_pair(x, y));
	}

	for (int i = 0; i < M; i++) {
		int x;
		cin >> x;

		auto it = Set.lower_bound(make_pair(x, 1));

		if (it != Set.end()) {
			cout << (*it).first << " " << (*it).second << "\n";
			Set.erase(*it);
		}
		else cout << "-1 -1\n";
	}
}