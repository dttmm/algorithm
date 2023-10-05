#include <iostream>
#include <algorithm>

using namespace std;

int X;
int Y;
int ans;

// 흥미로운 숫자인지 판별
bool solve(int n) {
	int cntArr[10] = {};	// 각 자리수의 개수를 담음
	while (n != 0) {
		int r = n % 10;
		cntArr[r]++;
		n /= 10;
	}

	int cnt1 = 0;	// 숫자가 한번 이산 나왔는지 카운트
	int cnt2 = 0;	// 숫자가 한번만 나왔는지 카운트
	for (int i = 0; i < 10; i++) {
		int c = cntArr[i];

		if (c == 1) cnt2++;
		if (c >= 1) cnt1++;
	}

	// 흥미로운 수를 만족하는지 판별
	return (cnt1 == 2 && cnt2 == 1);
}

int main() {

	// 입력
	cin >> X >> Y;

	// 완탐
	for (int i = X; i <= Y; i++) {
		bool ret = solve(i);

		if (ret) ans++;
	}

	cout << ans;
}