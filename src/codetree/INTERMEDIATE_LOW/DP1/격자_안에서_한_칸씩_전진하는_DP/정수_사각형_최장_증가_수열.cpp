#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 500

int N;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];	// 현재 위치에서의 최대값
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

// 범위 체크
bool isIn(int i, int j) {
	return i >= 0 && i < N&& j >= 0 && j < N;
}

// i, j 위치에서 최대값 구하기
int solve(int i, int j) {
	if (d[i][j] != 0) return d[i][j];

	// 4방향 탐색하면서 갈 수 있는 최대값 구함
	int maxVal = 0;
	for (int dir = 0; dir < 4; dir++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어나는 경우 패쓰
		if (!isIn(newI, newJ)) continue;
		// 갈 수 없는 경우 패쓰
		if (arr[i][j] >= arr[newI][newJ])continue;

		int ret = solve(newI, newJ);
		maxVal = max(maxVal, ret);
	}

	// 최대값에 자신 포함시켜서 저장
	return d[i][j] = maxVal + 1;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			solve(i, j);
		}
	}

	int maxVal = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			maxVal = max(maxVal, d[i][j]);
		}
	}

	cout << maxVal;
}