#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];

void init() {
	// 시작 지점 세팅
	d[0][0] = arr[0][0];

	// 첫 행 세팅
	for (int i = 1; i < N; i++) {
		d[i][0] = max(d[i - 1][0], arr[i][0]);
	}

	// 첫 열 세팅
	for (int j = 1; j < N; j++) {
		d[0][j] = max(d[0][j - 1], arr[0][j]);
	}
}

// dp
void solve() {
	for (int i = 1; i < N; i++) {
		for (int j = 1; j < N; j++) {
			// 위쪽, 왼쪽에서 최소값 선택
			int minVal = min(d[i - 1][j], d[i][j - 1]);
			// 최소값과 자신 중 최대값의 dp배열 업데이트
			d[i][j] = max(minVal, arr[i][j]);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	init();

	solve();

	cout << d[N - 1][N - 1];
}