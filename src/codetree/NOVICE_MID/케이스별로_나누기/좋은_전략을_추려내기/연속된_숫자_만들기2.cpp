#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

int main() {

	// 입력
	int a, b, c;
	cin >> a >> b >> c;

	int diff1 = abs(a - b);
	int diff2 = abs(b - c);
	int diff3 = abs(c - a);

	int cnt1 = 0;
	int cnt2 = 0;

	if (diff1 == 1) cnt1++;
	if (diff2 == 1) cnt1++;
	if (diff3 == 1) cnt1++;

	if (diff1 == 2) cnt2++;
	if (diff2 == 2) cnt2++;
	if (diff3 == 2) cnt2++;

	// 1차이 나는 수가 2개인 경우 -> 정렬할 필요 없음
	if (cnt1 == 2) {
		cout << 0;
		return 0;
	}
	// 2차이 나는 수가 1개 이상인 경우 -> 2차이 나는 수 사이로 나머지 수가 들어가면 됨
	if (cnt2 >= 1) {
		cout << 1;
		return 0;
	}
	cout << 2;
}