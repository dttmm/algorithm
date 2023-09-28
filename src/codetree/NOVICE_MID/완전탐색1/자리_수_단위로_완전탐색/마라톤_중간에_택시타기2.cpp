#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int xrr[MAX_N];
int yrr[MAX_N];

// k번 체크포인트를 건너 뛰었을 때 이동 거리
int solve(int k) {
	int x = xrr[0];
	int y = yrr[0];

	int d = 0;
	for (int i = 1; i < N; i++) {
		// 건너뛸 체크포인트
		if (i == k) continue;

		// 이동할 체크포인트
		int newX = xrr[i];
		int newY = yrr[i];

		// 거리 계산
		d += abs(x - newX);
		d += abs(y - newY);

		// 현재 위치 갱신
		x = newX;
		y = newY;
	}

	return d;
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;

		xrr[i] = x;
		yrr[i] = y;
	}

	// 완전 탐색
	int minValue = INT_MAX;
	for (int i = 1; i < N - 1; i++) {
		int ret = solve(i);

		minValue = min(minValue, ret);
	}

	cout << minValue;
}