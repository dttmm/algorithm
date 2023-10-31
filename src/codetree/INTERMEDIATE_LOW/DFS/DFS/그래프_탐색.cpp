#include <iostream>
#include <vector>
#include <stack>

using namespace std;

#define MAX_N 1000
#define MAX_M 10000

int N;
int M;
vector<int> list[MAX_N + 1];
bool visited[MAX_N + 1];

void solve() {
	// 1번 노드부터 dfs 시작
	stack<int> st;
	st.push(1);
	visited[1] = true;

	while (!st.empty()) {
		int v = st.top();
		st.pop();
		// 하고 싶은 작업 수행

		// 이웃 노드 돌기
		for (int u : list[v]) {
			if (visited[u]) continue;

			visited[u] = true;
			st.push(u);
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int x;
		int y;
		cin >> x >> y;

		list[x].push_back(y);
		list[y].push_back(x);
	}

	solve();

	int cnt = 0;
	for (int i = 2; i <= MAX_N; i++) {
		if (visited[i]) cnt++;
	}

	cout << cnt;
}