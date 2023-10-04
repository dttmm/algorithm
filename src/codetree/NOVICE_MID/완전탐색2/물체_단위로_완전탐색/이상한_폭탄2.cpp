#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_NUM 1000
#define MAX_N 100

int N;
int K;
int arr[MAX_NUM + 1];	// i번 폭탄의 최근 위치
int ans = -1;

int main() {

	// 입력 받기
	cin >> N >> K;

	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		// i번 폭탄이 처음 나온 경우
		if (arr[n] == 0) arr[n] = i;

		// i번 폭탄이 이미 나온적 있는 경우
		else {
			// 이전에 있던 위치와 현재 위치 차이가 K이내인지 체크
			if (i - arr[n] <= K) ans = max(ans, n);
			arr[n] = i;
		}
	}

	cout << ans;
}