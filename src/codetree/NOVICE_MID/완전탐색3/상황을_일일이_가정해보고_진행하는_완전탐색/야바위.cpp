#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int brr[MAX_N];
int crr[MAX_N];
int ans;

// 컵 스왑
void swap(int* cup, int a, int b) {
	int temp = *(cup + a);
	*(cup + a) = *(cup + b);
	*(cup + b) = temp;
}

void solve() {
	// 1~3번 컵에 조약돌 다 넣어보기
	for (int i = 1; i <= 3; i++) {
		int cup[4] = { 0,1,2,3 };	// i번 위치에 value컵이 있음
		int cnt = 0;

		// 입력 순회
		for (int k = 0; k < N; k++) {
			int a = arr[k];
			int b = brr[k];
			int c = crr[k];

			swap(cup, a, b);
			// c번 컵위치에 조약돌이 있던 컵인 경우
			if (cup[c] == i) cnt++;
		}

		ans = max(ans, cnt);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i] >> brr[i] >> crr[i];
	}

	solve();

	cout << ans;
}