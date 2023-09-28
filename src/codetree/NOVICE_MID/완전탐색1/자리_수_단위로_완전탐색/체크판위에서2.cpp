#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 15
#define R 3

int N;
int M;
int arr[MAX_N][MAX_N];	// 색 표시 1: 화이트, 2: 블랙
int cnt;

void solve(int k, int start_i, int start_j, int curColor) {
	// 3번 점프했을 때
	if (k == R) {
		// 끝 지점에 도달한 경우
		if (start_i == N && start_j == M) cnt++;
	}
	else {
		for (int i = start_i; i < N; i++) {
			for (int j = start_j; j < M; j++) {
				// 색이 같은 경우
				if (arr[i][j] * curColor > 0) continue;

				// 최소한 대각선 한칸 아래로 점프
				solve(k + 1, i + 1, j + 1, arr[i][j]);
			}
		}
	}
}

int main() {

	// 입력 받기
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			char c;
			cin >> c;
			if (c == 'W') arr[i][j] = 1;
			else arr[i][j] = -1;
		}
	}

	solve(0, 1, 1, arr[0][0]);

	cout << cnt;
}