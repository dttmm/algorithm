#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX_X 1000

int N;
int arr[MAX_N];	// 양수
int brr[MAX_N];	// 음수
int cntA;	// 양수 개수
int cntB;	// 음수 개수
int ans = MAX_X * MAX_X * MAX_X * -1;

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		if (n > 0) arr[cntA++] = n;
		else if (n < 0) brr[cntB++] = n;
		else ans = 0;	// 0이 있을 경우
	}

	sort(arr, arr + cntA);
	sort(brr, brr + cntB);

	// 양수 3개 고르는 경우
	if (cntA >= 3) {
		int total = 1;
		// 숫자 큰 양수 3개 고르기
		for (int i = 0; i < 3; i++) {
			total *= arr[cntA - 1 - i];
		}

		ans = max(ans, total);
	}

	// 양수 2개, 음수 1개 고르는 경우
	if (cntA >= 2 && cntB >= 1) {
		int total = 1;
		// 숫자 작은 양수 2개 고르고
		for (int i = 0; i < 2; i++) {
			total *= arr[i];
		}
		// 숫자 작은 음수 1개 고르기
		for (int i = 0; i < 1; i++) {
			total *= brr[cntB - 1 - i];
		}

		ans = max(ans, total);
	}

	// 양수 1개, 음수 2개 고르는 경우
	if (cntA >= 1 && cntB >= 2) {
		int total = 1;
		// 숫자 큰 양수 1개 고르고
		for (int i = 0; i < 1; i++) {
			total *= arr[cntA - 1 - i];
		}
		// 숫자 큰 음수 2개 고르기
		for (int i = 0; i < 2; i++) {
			total *= brr[i];
		}

		ans = max(ans, total);
	}

	// 음수 3개 고르는 경우
	if (cntB >= 3) {
		int total = 1;
		// 숫자 작은 음수 3개 고르기
		for (int i = 0; i < 3; i++) {
			total *= brr[cntB - 1 - i];
		}

		ans = max(ans, total);
	}

	cout << ans;
}