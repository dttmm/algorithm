#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000
#define MAX_X 1000

int N;
int d[MAX_N + 1];   // n���� ������ ������ ����� �� �ִ밪

struct Node {
	int start;
	int end;

}nodes[MAX_N];

// end ���� �� �켱
bool cmp(Node a, Node b) {
	return a.end < b.end;
}

// dp
void solve() {
	// ���� ���� ��Ƽ�
	// ���� ���� ��ġ�� �ʰ� �տ� �ִ� ���е� ��
	// �ִ밪 ã��
	for (int i = 0; i < N; i++) {
		int start = nodes[i].start;
		int end = nodes[i].end;

		for (int k = 0; k < start; k++) {
			d[end] = max(d[end], d[k] + 1);
		}
	}
}

int main() {

	// �Է�
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start, end;
		cin >> start >> end;
		nodes[i].start = start;
		nodes[i].end = end;
	}

	sort(nodes, nodes + N, cmp);

	solve();

	// �ִ밪 ã��
	int ans = 0;
	for (int i = 1; i <= MAX_X; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}