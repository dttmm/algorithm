#include <iostream>
#include <algorithm>
#include <unordered_set>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b));
#define MIN(a, b) ((a) < (b) ? (a) : (b));

struct Node {
	int x1;
	int x2;

	Node() {}
	Node(int x1_, int x2_) {
		x1 = x1_;
		x2 = x2_;
	}

}nodes[MAX_N + 2];

int N;
int LMax[MAX_N + 2]; // 0~i까지 x2의 최대값
int RMin[MAX_N + 2]; // i~N-1까지 x2의 최소값

void init() {
	for (int i = 0; i <= N + 1; i++) {
		LMax[i] = -1000000000;
	}

	for (int i = 0; i <= N + 1; i++) {
		RMin[i] = 1000000000;
	}
}

// x1 작은순으로 정렬
bool cmp(Node a, Node b) {
	return a.x1 < b.x1;
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 1; i <= N; i++) {
		int x1, x2;
		cin >> x1 >> x2;

		nodes[i] = Node(x1, x2);
	}

	init();

	// x1 작은순으로 정렬
	sort(nodes + 1, nodes + 1 + N, cmp);

	for (int i = 1; i <= N; i++) {
		LMax[i] = MAX(LMax[i - 1], nodes[i].x2);
	}

	for (int i = N; i > 0; i--) {
		RMin[i] = MIN(RMin[i + 1], nodes[i].x2);
	}

	int cnt = 0;
	for (int i = 1; i <= N; i++) {
		if (LMax[i] == RMin[i]) cnt++;
	}

	cout << cnt;
}