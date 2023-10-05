#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int ans = 1000000;

// 숫자를 제거하고 인접한 숫자간의 차의 합 최소 찾기
void solve() {
	// 제거할 원소 선택
	for (int i = 0; i < N; i++) {
		int brr[MAX_N] = {};
		int index = 0;

		// 나머지 원소 brr에 담음
		for (int j = 0; j < N; j++) {
			if (i == j) continue;
			brr[index++] = arr[j];
		}

		// 인접한 숫자산의 차의 합 구하기
		int sum = 0;
		for (int i = 1; i < N - 1; i++) {
			int diff = abs(brr[i] - brr[i - 1]);
			sum += diff;
		}

		ans = min(ans, sum);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 2배할 원소 선택
	for (int i = 0; i < N; i++) {
		arr[i] *= 2;
		solve();
		arr[i] /= 2;
	}

	cout << ans;
}