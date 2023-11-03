#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int M;
int d[MAX_M + 1];	// m���Ա��� ä���� �� �ִ밪

struct Node {
	int w;
	int v;
}nodes[MAX_N];

// dp
void solve() {
	// m���Ը� ä�� ���� �ִ밪 ���ϱ�
	for (int m = 1; m <= M; m++) {
		d[m] = d[m - 1];

		// ��� ���� ���鼭 �ִ밪 ����
		for (int i = 0; i < N; i++) {
			int w = nodes[i].w;
			int v = nodes[i].v;
			if (w > m) continue;

			d[m] = max(d[m], d[m - w] + v);
		}
	}
}

int main() {

	// �Է�
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		int w, v;
		cin >> w >> v;

		nodes[i].w = w;
		nodes[i].v = v;
	}

	solve();

	cout << d[M];
}