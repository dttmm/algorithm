#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int S;
int arr[MAX_N];

int main() {

	// 입력의 총 합
	int total = 0;

	// 입력 받기
	cin >> N >> S;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
		total += n;
	}

	// 정렬
	sort(arr, arr + N);

	int ans = INT_MAX;
	int L = 0;
	int R = N - 1;

	// 투포인터
	while (L < R) {
		int a = arr[L];
		int b = arr[R];

		// 선택한 두개 빼고 나머지들의 합
		int remain = total - a - b;
		// 나머지 들의 합과 S의 차이
		int ret = abs(remain - S);
		// 최소값 갱신
		ans = min(ans, ret);

		// 남은 수들의 합이 S보다 더 큰 경우
		// -> L이 더 큰 수를 선택해서 remain을 줄여야 함
		if (remain > S) L++;
		// 남은 수들의 합이 S보다 더 작은 경우
		// -> R이 더 작은 수를 선택해서 remain을 크게해야 함
		else if (remain < S) R--;
		// 남은 수들의 합이 S와 동일한 경우
		else break;
	}

	cout << ans;
}