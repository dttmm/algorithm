#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
vector<pair<int, int> > list;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		list.push_back(make_pair(a, 1));	// 시작 지점 저장
		list.push_back(make_pair(b, -1));	// 종료 지점 저장
	}

	// 정렬
	sort(list.begin(), list.end());

	int cnt = 0;
	int ans = 0;
	int prev;	// 구간 시작 지점
	for (auto it : list) {
		int x = it.first;
		int v = it.second;

		// 시작 지점인 경우
		if (v > 0) {
			// 구간 시작인 경우
			if (cnt == 0)prev = x;

			cnt++;
		}
		// 종료 지점인 경우
		else {
			cnt--;

			// 구간 끝난 경우
			if (cnt == 0) ans = MAX(ans, x - prev);
		}
	}

	cout << ans;
}