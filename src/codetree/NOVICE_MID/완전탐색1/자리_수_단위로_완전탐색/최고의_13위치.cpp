#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 20

int N;
int arr[MAX_N][MAX_N];
int ans;

// 완전 탐색
void solve() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N - 2; j++) {
			int num = arr[i][j] + arr[i][j + 1] + arr[i][j + 2];
			ans = max(ans, num);
		}
	}
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	solve();

	cout << ans;
}