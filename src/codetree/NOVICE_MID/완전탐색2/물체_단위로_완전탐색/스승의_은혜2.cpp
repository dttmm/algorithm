#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int B;
int arr[MAX_N];
int ans;

void solve() {
	// 반값 할인할 학생 I하나 고르고
	for (int i = 0; i < N; i++) {
		int total = 0;	// 총 선물 금액
		int cnt = 0;	// 선물 받을 인원

		// 제일 싼 선물부터 사줌
		for (int j = 0; j < N; j++) {
			total += arr[j];

			// 반값 할인 대상
			if (i == j) total -= arr[j] * 0.5;

			// 예산 넘어가는 경우
			if (total > B) break;

			cnt++;
			ans = max(ans, cnt);
		}
	}
}

int main() {

	// 입력 받기
	cin >> N >> B;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 낮은순으로 정렬
	sort(arr, arr + N);

	solve();

	cout << ans;
}