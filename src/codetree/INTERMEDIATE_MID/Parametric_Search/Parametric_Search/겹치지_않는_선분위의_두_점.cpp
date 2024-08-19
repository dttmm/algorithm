#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000

int N;
int M;

struct Node {
	long long a;
	long long b;

	Node() {};

}nodes[MAX_N];

bool cmp(Node a, Node b) {
	return a.a < b.a;
}


// 점 사이의 거리를 x로 N개 두는 것이 가능한지
bool isPossible(long long x) {
	int cnt = 1;
	long long prev = nodes[0].a;

	for (int i = 0; i < M; i++) {
		long long a = nodes[i].a;
		long long b = nodes[i].b;

		while (prev + x <= b) {
			if (prev + x < a) prev = a;
			else prev += x;

			cnt++;
		}

		if (cnt >= N) break;
	}
	return cnt >= N;
}

long long solve() {
	long long s = 1;
	long long e = 2e18;
	long long ans = -1;

	while (s <= e) {
		long long mid = s + (e - s) / 2;

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

	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		cin >> nodes[i].a >> nodes[i].b;
	}

	sort(nodes, nodes + M, cmp);

	long long ret = solve();

	cout << ret;
}