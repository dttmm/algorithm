#include <iostream>
#include <set>
#include <map>

using namespace std;

#define MAX_N 100000

int N;
int Q;
set<int> Set;
map<int, int > Map;

// 나보다 크거나 같은 놈
int getCeil(int n) {
	auto it = Map.lower_bound(n);

	if (it == Map.end()) return -1;

	return it->first;
}

// 나보다 작거나 같은 놈
int getFloor(int n) {
	auto it = Map.upper_bound(n);

	if (it == Map.begin()) return -1;

	it--;
	return it->first;
}

int main() {

	cin >> N >> Q;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	int cnt = 1;
	for (int it : Set) {
		Map[it] = cnt++;
	}

	for (int i = 0; i < Q; i++) {
		int a, b;
		cin >> a >> b;

		a = getCeil(a);
		b = getFloor(b);

		if (a == -1 || b == -1) cout << 0 << "\n";
		else cout << Map[b] - Map[a] + 1 << "\n";
	}
}