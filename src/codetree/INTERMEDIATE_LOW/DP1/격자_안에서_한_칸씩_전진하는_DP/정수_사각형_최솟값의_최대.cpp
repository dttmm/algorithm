#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];

// i, j에서 끝까지 가는 경로에서의 최소값의 최대값 반환
int solve(int i, int j) {
	if (d[i][j] != 0) return d[i][j];

	int maxVal = 0;

	// 아래쪽으로 갈 수 있는 경우
	if (i + 1 < N) {
		int ret = solve(i + 1, j);
		maxVal = max(maxVal, ret);
	}

	// 오른쪽으로 갈 수 있는 경우
	if (j + 1 < N) {
		int ret = solve(i, j + 1);
		maxVal = max(maxVal, ret);
	}

	// 자기 자신을 포함하여 dp배열 업데이트
	if (maxVal != 0)d[i][j] = min(maxVal, arr[i][j]);
	else d[i][j] = arr[i][j];

	return d[i][j];
}

int main() {

	// 입력
	cin >> N;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	solve(0, 0);

	cout << d[0][0];
}