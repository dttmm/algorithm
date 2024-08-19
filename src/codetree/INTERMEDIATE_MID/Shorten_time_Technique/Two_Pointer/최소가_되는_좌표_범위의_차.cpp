#include <bits/stdc++.h>
using namespace std;

#define MAX_N 100000

pair<int, int> arr[MAX_N];	// first: x, second: y
int N;
int D;
map<int, int> Map;	// y좌표 저장

int main() {

	cin >> N >> D;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		arr[i] = { x, y };
	}

	// x순으로 정렬
	sort(arr, arr + N);

	int ans = INT_MAX;
	int e = 0;
	for (int s = 0; s < N; s++) {
		// 시작점
		int sx, sy;
		tie(sx, sy) = arr[s];

		while (e < N) {
			//끝 점
			int ex, ey;
			tie(ex, ey) = arr[e];

			Map[ey]++;

			// y좌표 차
			int diffY = abs((*Map.rbegin()).first - (*Map.begin()).first);
			if (diffY >= D) {
				int diffX = ex - sx;
				ans = min(ans, diffX);
				Map[ey]--;
				break;
			}
			e++;
		}

		// 시작점 한칸 이동
		Map[sy]--;
		if (Map[sy] == 0)Map.erase(sy);
	}

	if (ans == INT_MAX) ans = -1;
	cout << ans;
}