#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 200

int N;
int xrr[MAX_N];
int yrr[MAX_N];
int ans = 100;

void solve() {
	// 홀수 값에 직선 긋기
	for (int i = 1; i < MAX_X; i++) {
		if (i % 2 == 0) continue;
		for (int j = 1; j < MAX_X; j++) {
			if (j % 2 == 0) continue;

			// 그은 직선을 기준으로 사분면 나눔
			int area1 = 0;	// 1사분면에 있는 점의 개수
			int area2 = 0;	// 2사분면에 있는 점의 개수
			int area3 = 0;	// 3사분면에 있는 점의 개수
			int area4 = 0;	// 4사분면에 있는 점의 개수

			// 각 사분면에 점들 몇 개인지 세기
			for (int k = 0; k < N; k++) {
				int x = xrr[k];
				int y = yrr[k];

				if (x > i && y > j) area1++;
				else if (x < i && y > j) area2++;
				else if (x < i && y < j) area3++;
				else if (x > i && y < j) area4++;
			}

			// 가장 많은 수의 점이 있는 사분면 구하기
			int maxVal = max(area1, area2);
			maxVal = max(maxVal, area3);
			maxVal = max(maxVal, area4);

			ans = min(ans, maxVal);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;

		// 좌표값 2배 늘려줌
		xrr[i] = 2 * x;
		yrr[i] = 2 * y;
	}

	solve();

	cout << ans;
}