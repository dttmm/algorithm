#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 20
#define MAX_X 10

int N;
bool xyrr[MAX_X + 1][MAX_X + 1];
int ans;

// X축에 평행인 선 그리기
void drawX(bool arr[][MAX_X + 1], int n) {
	for (int i = 0; i <= MAX_X; i++) {
		*(*(arr + n) + i) = false;
	}
}

// Y축에 평행인 선 그리기
void drawY(bool arr[][MAX_X + 1], int n) {
	for (int i = 0; i <= MAX_X; i++) {
		*(*(arr + i) + n) = false;
	}
}

// 완탐
void solve(int k, bool arr[][MAX_X + 1]) {
	// 선 3개를 다 그린 경우
	if (k == 3) {
		// 모든 점을 지날 수 있는지 검사
		for (int i = 0; i <= MAX_X; i++) {
			for (int j = 0; j <= MAX_X; j++) {
				if (arr[i][j] == true) return;
			}
		}
		ans = 1;
	}
	else {
		for (int i = 0; i <= MAX_X; i++) {
			bool brr[MAX_X + 1][MAX_X + 1] = {};
			copy(&arr[0][0], &arr[0][0] + (MAX_X + 1) * (MAX_X + 1), &brr[0][0]);

			// X축 그리고
			drawX(brr, i);
			// 다음 선분 그리기
			solve(k + 1, brr);

			bool crr[MAX_X + 1][MAX_X + 1] = {};
			copy(&arr[0][0], &arr[0][0] + (MAX_X + 1) * (MAX_X + 1), &crr[0][0]);

			// Y축 그리고
			drawY(crr, i);
			// 다음 선분 그리기
			solve(k + 1, crr);
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
		xyrr[x][y] = true;
	}

	solve(0, xyrr);

	cout << ans;
}