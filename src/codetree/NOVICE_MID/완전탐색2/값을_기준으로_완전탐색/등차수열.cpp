#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int ans;

void solve() {
	// k 하나 고르기
	for (int k = 1; k <= 100; k++) {
		int total = 0;	// k일 때 총 경우의 수

		for (int i = 0; i < N; i++) {
			int n = arr[i];		// k 앞에 있는 숫자
			int diff = k - n;
			int m = k + diff;	// 등차수열을 이루기 위해 k 뒤에 있어야 하는 숫자

			for (int j = i + 1; j < N; j++) {
				if (arr[j] == m) {	// 해당 숫자 m이 k 뒤에 있는지 확인
					total++;
					break;
				}
			}
		}
		ans = max(ans, total);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << ans;
}