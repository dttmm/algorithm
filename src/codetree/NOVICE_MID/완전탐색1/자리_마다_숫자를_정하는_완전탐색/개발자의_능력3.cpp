#include <iostream>
#include <climits>
#include<algorithm>
using namespace std;

#define R 3
#define N 6

int arr[N];
int total;	// 전체 개발자의 총 능력의 합
int ans = INT_MAX;

// 조합
void solve(int k, int start, int sum) {
	// 3개를 뽑았을 경우
	if (k == R) {
		int sum2 = total - sum;
		int diff = abs(sum - sum2);
		ans = min(ans, diff);
	}
	else {
		for (int i = start; i < N; i++) {
			int n = arr[i];
			solve(k + 1, i + 1, sum + n);
		}
	}
}

int main() {

	// 입력 받기
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		arr[i] = n;
		total += n;
	}

	// 조합
	solve(0, 0, 0);

	cout << ans;
}