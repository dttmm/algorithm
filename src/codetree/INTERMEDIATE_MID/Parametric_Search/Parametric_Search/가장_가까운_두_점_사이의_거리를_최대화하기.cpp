#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;

struct Node {
	int a;
	int b;

	Node() {};
}nodes[MAX_N];

bool cmp(Node a, Node b) {
	return a.a < b.a;
}

// 점 사이의 거리를 x로 했을 때 가능한지
bool isPossible(int x) {
	int prev = nodes[0].a;

	for (int i = 1; i < N; i++) {
		int a = nodes[i].a;
		int b = nodes[i].b;

		if (prev + x > b) return false;

		prev = MAX(prev + x, a);
	}

	return true;
}

int solve() {
	int s = 1;
	int e = 1000000000;
	int ans = -1;

	while (s <= e) {
		int mid = s + (e - s) / 2;

		if (isPossible(mid)) {
			ans = mid;
			s = mid + 1;
		}
		else {
			e = mid - 1;
		}
	}

	return ans;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> nodes[i].a;
		cin >> nodes[i].b;
	}

	sort(nodes, nodes + N, cmp);

	int ret = solve();

	cout << ret;
}