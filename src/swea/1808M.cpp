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
stack<tuple<int, int, int> > st;	// 노드 번호, 비용(거리), 이전 노드 번호
int visited[MAX_N + 1];	// i노드에 첫 방문시 비용 저장

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

		// 사이클 발견한 경우 -> 지금까지 비용과 해당 노드를 처음 갔을 때의 비용 차
		if (visited[v] != 0) return cost - visited[v];
		visited[v] = cost;

		for (int u : lists[v]) {
			// 바로 이전 노드로 가는 경우 방지
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