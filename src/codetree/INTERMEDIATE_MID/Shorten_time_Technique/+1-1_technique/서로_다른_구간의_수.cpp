#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

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

		list.push_back(make_pair(a, 1));	// 시작 지점
		list.push_back(make_pair(b, -1));	// 종료 지점
	}

	// 정렬
	sort(list.begin(), list.end());

	int ans = 0;	// 그룹 개수
	int cnt = 0;	// 현재까지 선분 개수
	for (auto it : list) {
		// 시작지점인 경우
		if (it.second > 0) {

			// 첫 시작인 경우 -> 그룹 생성
			if (cnt == 0) ans++;

			cnt++;
		}
		// 종료지점인 경우
		else {
			cnt--;
		}
	}

	cout << ans;
}