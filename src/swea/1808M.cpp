#include <iostream>
#include <vector>
#include <tuple>
#include <stack>

using namespace std;

/*
* in 1808 out 8424
*/

#define MAX_N 1000

int N;
vector<int> lists[MAX_N + 1];
stack<tuple<int, int, int> > st;	// ��� ��ȣ, ���(�Ÿ�), ���� ��� ��ȣ
int visited[MAX_N + 1];	// i��忡 ù �湮�� ��� ����

void init() {
	for (int i = 1; i <= N; i++) {
		lists[i] = vector<int>();
	}

	st = stack<tuple<int, int, int> >();

	for (int i = 1; i <= N; i++) visited[i] = 0;
}

int solve() {

	st.push(make_tuple(1, 1, 0));

	while (!st.empty()) {
		auto it = st.top();
		st.pop();

		int v, cost, prev;
		tie(v, cost, prev) = it;

		// ����Ŭ �߰��� ��� -> ���ݱ��� ���� �ش� ��带 ó�� ���� ���� ��� ��
		if (visited[v] != 0) return cost - visited[v];
		visited[v] = cost;

		for (int u : lists[v]) {
			// �ٷ� ���� ���� ���� ��� ����
			if (u == prev) continue;

			st.push(make_tuple(u, cost + 1, v));
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {

		cin >> N;

		init();

		for (int i = 0; i < N; i++) {
			int a, b;
			cin >> a >> b;

			lists[a].push_back(b);
			lists[b].push_back(a);
		}

		int ret = solve();

		cout << "#" << tc << " " << ret << "\n";
	}
}