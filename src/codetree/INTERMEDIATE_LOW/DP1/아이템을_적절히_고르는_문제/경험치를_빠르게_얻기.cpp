#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_M 1000000
#define INF 1000000000

int N;
int M;
int d[MAX_M + 1];	// ����ġ m�� ��µ� �ɸ��� �ּ� �ð�

struct Node {
	int e;
	int t;
}nodes[MAX_N];

// �ʱ�ȭ
void init() {
	for (int i = 1; i <= MAX_M; i++) {
		d[i] = INF;
	}
}

// dp
void solve() {
	// ����Ʈ i�� �����Ҷ�
	for (int i = 0; i < N; i++) {
		int e = nodes[i].e;
		int t = nodes[i].t;

		// ���� �� �ִ� ����ġ���� �ɸ��� �� �ּҸ� ����
		for (int m = MAX_M; m >= 1; m--) {
			if (e > m) continue;

			d[m] = min(d[m], d[m - e] + t);
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int e, t;
		cin >> e >> t;

		nodes[i].e = e;
		nodes[i].t = t;
	}

	init();

	solve();

	// ����ġ M�̻� ��µ� �ɸ��� �ּ� �ð� ���ϱ�
	int ans = INF;
	for (int i = M; i <= MAX_M; i++) {
		ans = min(ans, d[i]);
	}

	if (ans == INF) ans = -1;
	cout << ans;
}