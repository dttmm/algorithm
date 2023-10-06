#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N + 1];
int ans;

void solve() {
	// 시작 위치 정하기
	for (int i = 1; i <= N; i++) {
		int x = i;

		// M번 만큼 움직이기
		int sum = 0;
		for (int j = 0; j < M; j++) {
			int n = arr[x];	// 해당 위치에 있는 숫자
			sum += n;
			x = n;			// 다음 위치 업데이트
		}

		ans = max(ans, sum);
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << ans;
}