#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 19

int N = MAX_N;
int arr[MAX_N][MAX_N];
int di[] = { -1,-1,0,1,1,1,0,-1 };
int dj[] = { 0,1,1,1,0,-1,-1,-1 };
int ansI;
int ansJ;

// 범위 체크
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

// 8방향 검사
int solve(int start_i, int start_j, int color) {
	for (int dir = 0; dir < 8; dir++) {
		int n = 4;
		int i = start_i;
		int j = start_j;

		// 현재 지점에서 dir 방향으로 4칸 검사
		while (n > 0) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// 범위 벗어난 경우
			if (!isIn(newI, newJ)) break;
			// 색이 다를 경우
			if (arr[newI][newJ] != color) break;

			i = newI;
			j = newJ;
			n--;

			// 오목의 중간 지점
			if (n == 2) {
				ansI = i;
				ansJ = j;
			}
		}

		// 오목을 완성한 경우
		if (n == 0) return color;
	}
	return 0;
}

int main() {

	// 입력받기
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	// 전체 바둑알 탐색
	int ret = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// 돌이 놓여있지 않은 경우
			if (arr[i][j] == 0) continue;

			ret = solve(i, j, arr[i][j]);
			if (ret != 0) break;
		}
		if (ret != 0) break;
	}

	if (ret == 0)cout << 0;
	else cout << ret << "\n" << ansI + 1 << " " << ansJ + 1;
}