#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 200

int N;
int M;
int d[MAX_N + 1][MAX_N];	// m�ϳ� i���� �Ծ��� ���� �ִ� ������

struct Node {
	int s;
	int e;
	int v;
}nodes[MAX_N];

void solve() {
	// �ʱⰪ ����
	for (int i = 0; i < N; i++) {
		int s = nodes[i].s;

		if (s != 1) d[1][i] = -1;
	}

	// m���� ��
	for (int m = 2; m <= M; m++) {
		// �� ���� ���� �� �ִ��� Ȯ��
		for (int i = 0; i < N; i++) {
			int s = nodes[i].s;
			int e = nodes[i].e;
			int v = nodes[i].v;

			// ���� ���� �� ���� ���
			if (s > m || e < m) {
				d[m][i] = -1;
				continue;
			}

			// ������ �Ծ��� �ʵ� Ȯ��
			for (int j = 0; j < N; j++) {
				// ������ ���� �� ������ ���� ���
				if (d[m - 1][j] == -1) continue;

				// ������ �Ծ��� �ʰ��� ������ ���
				int diff = abs(nodes[j].v - v);

				// ������ j�� �Ծ��� ���� �ִ� ������ + ���� �ʰ��� ������ ����Ͽ�
				// ���� i���� �Ծ��� ���� �ִ� ������ ����
				d[m][i] = max(d[m][i], d[m - 1][j] + diff);
			}
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int s, e, v;
		cin >> s >> e >> v;

		nodes[i].s = s;
		nodes[i].e = e;
		nodes[i].v = v;
	}

	solve();

	// M�ϳ� ������ �ִ� ������ ã��
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d[M][i]);
	}

	cout << ans;
}