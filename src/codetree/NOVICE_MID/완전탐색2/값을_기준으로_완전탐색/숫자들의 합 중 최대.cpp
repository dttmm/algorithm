#include <iostream>
#include <algorithm>

using namespace std;

int X;
int Y;
int ans;

// 완탐
void solve() {
	for (int i = X; i <= Y; i++) {
		int n = i;
		int sum = 0;

		// 자리 숫자 더하기
		while (n != 0) {
			sum += n % 10;
			n /= 10;
		}

		ans = max(ans, sum);
	}
}

int main() {

	// 입력
	cin >> X >> Y;

	// 완탐
	solve();

	cout << ans;
}