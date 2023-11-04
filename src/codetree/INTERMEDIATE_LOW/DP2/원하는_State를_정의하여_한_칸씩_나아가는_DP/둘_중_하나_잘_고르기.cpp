#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define INF -1000000

int N;
int red[2 * MAX_N + 1];
int blue[2 * MAX_N + 1];
int d[2 * MAX_N + 1][MAX_N + 1];	// n번째 선택에 빨간색을 선택한 횟수가 i일때 최대값

// dp
void solve() {
	// dp배열 초기화 <- 최대값 구할거니까 가장 작은 음수로 전환
	for (int n = 0; n <= 2 * N; n++) {
		for (int i = 0; i <= N; i++) {
			d[n][i] = INF;
		}
	}
	// 0번째에 빨간색을 선택하지 않으면 0점임
	d[0][0] = 0;

	// n번째에
	for (int n = 1; n <= 2 * N; n++) {
		// 빨간색을 i번 선택한 경우
		for (int i = 0; i <= N; i++) {
			// 파란색을 선택한 횟수는 n-i번이 됨
			// 파란색을 선택한 횟수가 N을 넘어가면 안됨
			if (n - i > N) continue;

			// 이전 결과에서 파란색을 선택하는 경우
			d[n][i] = max(d[n][i], d[n - 1][i] + blue[n]);

			if (i - 1 >= 0) {
				// 이전 결과에서 빨간색을 선택하는 경우
				d[n][i] = max(d[n][i], d[n - 1][i - 1] + red[n]);
			}
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 1; i <= 2 * N; i++) {
		int r, b;
		cin >> r >> b;

		red[i] = r;
		blue[i] = b;
	}

	solve();

	// 마지막번쨰의 최대값 찾기
	int ans = 0;
	for (int i = 0; i <= N; i++) {
		ans = max(ans, d[2 * N][i]);
	}

	cout << ans;
}