#include <bits/stdc++.h>
using namespace std;

#define MAX_N 100000

pair<int, int> arr[MAX_N];	// first: x, second: y
int N;
int D;
map<int, int> Map;	// y��ǥ ����

int main() {

	cin >> N >> D;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		arr[i] = { x, y };
	}

	// x������ ����
	sort(arr, arr + N);

	int ans = INT_MAX;
	int e = 0;
	for (int s = 0; s < N; s++) {
		// ������
		int sx, sy;
		tie(sx, sy) = arr[s];

		while (e < N) {
			//�� ��
			int ex, ey;
			tie(ex, ey) = arr[e];

			Map[ey]++;

			// y��ǥ ��
			int diffY = abs((*Map.rbegin()).first - (*Map.begin()).first);
			if (diffY >= D) {
				int diffX = ex - sx;
				ans = min(ans, diffX);
				Map[ey]--;
				break;
			}
			e++;
		}

		// ������ ��ĭ �̵�
		Map[sy]--;
		if (Map[sy] == 0)Map.erase(sy);
	}

	if (ans == INT_MAX) ans = -1;
	cout << ans;
}