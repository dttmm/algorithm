#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 20

int N;
int arr[MAX_N][MAX_N];

// 완탐
int solve() {
	int maxVal = 0;

	// 기준 i, j설정
	for (int i = 0; i <= N - 3; i++) {
		for (int j = 0; j <= N - 3; j++) {
			int cnt = 0;

			// 기준으로 부터 3*3 범위 안의 동전 개수 카운트
			for (int ii = 0; ii < 3; ii++) {
				for (int jj = 0; jj < 3; jj++) {
					cnt += arr[i + ii][j + jj];
				}
			}

			maxVal = max(maxVal, cnt);
		}
	}

	return maxVal;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	int ret = solve();

	cout << ret;

}