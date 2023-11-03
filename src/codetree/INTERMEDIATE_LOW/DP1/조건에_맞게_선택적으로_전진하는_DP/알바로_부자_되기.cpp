#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int d[MAX_N];	// i�˹ٱ��� ������ ���� �� �ִ� �ִ� �ݾ�

struct Node {
	int s;
	int e;
	int p;
}nodes[MAX_N];

// dp
void solve() {

	for (int i = 0; i < N; i++) {
		int s = nodes[i].s;
		int e = nodes[i].e;
		int p = nodes[i].p;

		// �ʱⰪ ����
		d[i] = p;

		// i�˹ٸ� �ϸ鼭
		// i�˹ٺ��� �տ� �ִ�
		// j�˹ٱ��� ���� ���� ��
		// i�˹ٱ��� ���� �� ���� �� �ִ� �ִ� �ݾ� ����
		for (int j = 0; j < i; j++) {
			if (nodes[j].e >= s) continue;
			d[i] = max(d[i], d[j] + p);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int s, e, p;
		cin >> s >> e >> p;

		nodes[i].s = s;
		nodes[i].e = e;
		nodes[i].p = p;
	}

	solve();

	// �ִ밪 ã��
	int ans = 0;
	for (int i = 0; i < MAX_N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}