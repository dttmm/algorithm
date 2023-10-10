#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1000

int N;
int K;
int arr[MAX_N];
int ans;

void solve(int start) {
	int maxVal = arr[start];
	int minVal = arr[start];	// start인덱스는 최소값으로 고정

	int cnt = 0;
	for (int i = start; i < N; i++) {
		int n = arr[i];
		maxVal = max(maxVal, n);	// 최대값 갱신

		// 최고 최소 차이가 K보다 크면 -> 끝
		if (maxVal - minVal > K) break;

		cnt++;
	}
	ans = max(ans, cnt);
}

int main() {

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 작은 숫자 우선 정렬
	sort(arr, arr + N);

	// i를 최소값으로 잡고 완탐
	for (int i = 0; i < N; i++) {
		solve(i);
	}

	cout << ans;
}