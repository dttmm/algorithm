#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 1000000

int N;
int K;
int arr[MAX_N];
int boom[MAX_X + 1];

// 최대값 찾아서 리턴
// K이내라는 말은 나 포함 K+1개를 선택한다는 말과 동일
int solve() {
	int cnt[MAX_X + 1]; // 해당 번호가 몇 개 선택되었는지 저장
	// 자신 포함 K+1개 선택해가면 슬라딩 윈도우
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// K+1개를 아직 선택하지 못한 경우
		if (i < K + 1) {
			cnt[n]++;
			if (cnt[n] > 1) boom[n]++;	// 고른 번호가 터질 수 있다면
			continue;
		}
		// K+1개를 선택한 경우

		cnt[n]++;

		int prev = arr[i - (K + 1)];
		cnt[prev]--;
		if (cnt[n] > 1) boom[n]++;		// 고른 번호가 터질 수 있다면
	}

	// 최대값 찾기
	int maxN = 0;
	for (int n = 1; n <= MAX_X; n++) {
		if (boom[n] >= boom[maxN]) maxN = n;
	}

	// 터진 폭탄이 없는 경우
	if (boom[maxN] == 0) maxN = 0;
	return maxN;
}

int main() {

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ret = solve();

	cout << ret;
}