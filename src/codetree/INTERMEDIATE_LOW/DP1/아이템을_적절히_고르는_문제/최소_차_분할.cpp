#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_N 100
#define MAX_X 100000

int N;
int arr[MAX_N];
bool d[MAX_X + 1];	// 숫자들을 합쳐서 n을 만들 수 있는지 여부
int sum;	// 모든 숫자들의 합

// dp
void solve() {
	d[0] = true;

	// 숫자 하나씩 뽑아서
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// 합 x를 만들수 있는지 검사
		for (int x = sum; x >= n; x--) {
			if (d[x - n]) {
				d[x] = true;
			}
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		sum += arr[i];
	}

	solve();

	// 합 i를 만족하는 그룹 A와
	// 나머지 합 sum-i를 만족하는 그룹간의 차의 최소를 구함
	int ans = MAX_X;
	for (int i = 1; i < sum; i++) {
		if (!d[i]) continue;

		int diff = i - (sum - i);
		ans = min(ans, abs(diff));
	}

	cout << ans;
}