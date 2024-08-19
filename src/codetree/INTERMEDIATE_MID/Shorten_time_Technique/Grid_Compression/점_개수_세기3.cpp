#include <iostream>
#include <set>
#include <unordered_map>

using namespace std;

int N;
int Q;
set<int> Set;
unordered_map<int, int> Map;

int main() {

	cin >> N >> Q;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		Set.insert(n);
	}

	int cnt = 0;
	for (int it : Set) {
		Map[it] = cnt++;
	}

	for (int q = 0; q < Q; q++) {
		int a, b;
		cin >> a >> b;

		int ret = Map[b] - Map[a] + 1;
		cout << ret << "\n";
	}
}