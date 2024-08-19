#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000
#define MAX_X 1000000000
#define MIN(a, b) ((a) < (b)) ? (a) : (b)
#define MAX(a, b) ((a) > (b)) ? (a) : (b)

int N;
int M;
int arr[MAX_N]; // 불 날 장소
int brr[MAX_N + 1];	// 소방서

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < M; i++) {
		cin >> brr[i];
	}

	brr[M] = MAX_X; // 마지막 지점 세팅
	sort(arr, arr + N);
	sort(brr, brr + M);

	int ans = 0;
	int L = 0;  // 불 날 곳 기준으로 왼쪽 소방성
	int R = 0;  // 불 날 곳 기준으로 오른쪽 소방성
	// 불 날 곳을 하나씩 탐색
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		if (n < brr[L]) continue;
		while (R < M && n > brr[R]) {
			L = R;
			R++;
		}

		// 더 가까운 소방서 고르기
		int minVal = MIN(n - brr[L], brr[R] - n);
		ans = MAX(ans, minVal);
	}

	cout << ans;
}