#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define INF -2000000000

int N;
int K;
int cur;
vector<pair<int, int> > list;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int x;
		char c;
		cin >> x >> c;

		// 생성되는 선분의 시작지점 종룔 지점 저장
		if (c == 'R') {
			list.push_back(make_pair(cur, 1));

			cur += x;
			list.push_back(make_pair(cur, -1));
		}
		else {
			list.push_back(make_pair(cur, -1));

			cur -= x;
			list.push_back(make_pair(cur, 1));
		}
	}

	// 정렬
	sort(list.begin(), list.end());

	int ans = 0;
	int cnt = 0;
	int start = INF; // 구간 시작 지점
	for (auto it : list) {
		cnt += it.second;

		// K개 이상 겹치는 곳
		if (cnt >= K) {
			// K개 이상 겹치는 곳의 시작
			if (start == INF) start = it.first;
		}
		else {
			// K개 이상 겹치는 곳의 종료
			if (start != INF) ans += it.first - start;

			start = INF;
		}
	}

	cout << ans;
}