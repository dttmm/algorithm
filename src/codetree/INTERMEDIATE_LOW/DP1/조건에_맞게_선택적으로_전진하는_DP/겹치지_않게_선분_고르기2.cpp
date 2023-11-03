#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000
#define MAX_X 1000

int N;
int d[MAX_N + 1];   // n으로 끝나는 선분을 잡았을 때 최대값

struct Node {
	int start;
	int end;

}nodes[MAX_N];

// end 낮은 게 우선
bool cmp(Node a, Node b) {
	return a.end < b.end;
}

// dp
void solve() {
	// 기준 선분 잡아서
	// 기준 보다 겹치지 않게 앞에 있는 선분들 중
	// 최대값 찾음
	for (int i = 0; i < N; i++) {
		int start = nodes[i].start;
		int end = nodes[i].end;

		for (int k = 0; k < start; k++) {
			d[end] = max(d[end], d[k] + 1);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start, end;
		cin >> start >> end;
		nodes[i].start = start;
		nodes[i].end = end;
	}

	sort(nodes, nodes + N, cmp);

	solve();

	// 최대값 찾기
	int ans = 0;
	for (int i = 1; i <= MAX_X; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}