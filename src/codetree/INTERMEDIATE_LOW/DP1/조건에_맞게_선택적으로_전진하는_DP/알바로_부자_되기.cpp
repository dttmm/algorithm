#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int d[MAX_N];	// i알바까지 했을때 얻을 수 있는 최대 금액

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

		// 초기값 세팅
		d[i] = p;

		// i알바를 하면서
		// i알바보다 앞에 있는
		// j알바까지 같이 했을 때
		// i알바까지 했을 때 얻을 수 있는 최대 금액 갱신
		for (int j = 0; j < i; j++) {
			if (nodes[j].e >= s) continue;
			d[i] = max(d[i], d[j] + p);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		int s, e, p;
		cin >> s >> e >> p;

		nodes[i].s = s;
		nodes[i].e = e;
		nodes[i].p = p;
	}

	solve();

	// 최대값 찾기
	int ans = 0;
	for (int i = 0; i < MAX_N; i++) {
		ans = max(ans, d[i]);
	}

	cout << ans;
}