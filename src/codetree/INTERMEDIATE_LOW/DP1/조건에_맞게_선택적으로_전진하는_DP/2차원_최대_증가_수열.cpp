#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 50

int N;
int M;
int arr[MAX_N][MAX_N];
int d[MAX_N][MAX_N];

// dp
void solve() {
	// 초기화
	d[0][0] = 1;

	// 기준 i, j를 잡아서
	for (int i = 1; i < N; i++) {
		for (int j = 1; j < M; j++) {
			// i,j 보다 좌측 상단에 있는 지점들 중
			// i,j로 올 수 있는 경우를 찾아서
			// 점프 최대값 업데이트
			for (int ii = 0; ii < i; ii++) {
				for (int jj = 0; jj < j; jj++) {
					// 시작지점인 1,1에서 올 수 없는 경우 패쓰
					if (d[ii][jj] == 0) continue;
					// 작은 수에서 큰 수로 오지 않는 경우 패쓰
					if (arr[ii][jj] >= arr[i][j]) continue;

					d[i][j] = max(d[i][j], d[ii][jj] + 1);
				}
			}
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
		}
	}

	solve();

	int ans = 0;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			ans = max(ans, d[i][j]);
		}
	}

	cout << ans;
}