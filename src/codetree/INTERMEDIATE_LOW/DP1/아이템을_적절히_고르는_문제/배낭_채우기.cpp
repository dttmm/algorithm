#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int M;
int d[MAX_M + 1];

struct Node {
	int w;
	int v;
}nodes[MAX_N];

void solve() {
	for (int i = 0; i < N; i++) {
		int w = nodes[i].w;
		int v = nodes[i].v;

		for (int m = M; m >= 1; m--) {
			if (w > m) break;

			d[m] = max(d[m], d[m - w] + v);
		}
	}
}

int main() {

	// ют╥б
	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		int w;
		int v;
		cin >> w >> v;

		nodes[i].w = w;
		nodes[i].v = v;
	}

	solve();

	cout << d[M];
}