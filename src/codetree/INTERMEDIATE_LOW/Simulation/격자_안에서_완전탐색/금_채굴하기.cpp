#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 20

int N;
int M;
int arr[MAX_N][MAX_N];

// 범위 체크
bool isIn(int i, int j) {
	if (i >= 0 && i < N && j >= 0 && j < N)return true;
	return false;
}

// 완탐
int solve() {
	int maxGold = 0;

	// 기준 i, j 정함
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			// 범위 k 정함
			for (int k = 0; k <= (N - 1) * 2; k++) {
				int  cost = k * k + (k + 1) * (k + 1);

				// 기준 i,j에서 k만큼 떨어진 거리에서 금화 줍줍
				int cnt = 0;
				for (int newI = i - k; newI <= i + k; newI++) {
					for (int newJ = j - k; newJ <= j + k; newJ++) {
						// 움직일 수 있는 범위 벗어난 경우 패쓰
						if (abs(newI - i) + abs(newJ - j) > k) continue;
						// 범위 벗어난 경우 패쓰
						if (!isIn(newI, newJ)) continue;

						cnt += arr[newI][newJ];
					}
				}
				// 이득 계산
				int diff = (cnt * M) - cost;
				if (diff >= 0) maxGold = max(maxGold, cnt);
			}
		}
	}

	return maxGold;
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	int ret = solve();

	cout << ret;
}