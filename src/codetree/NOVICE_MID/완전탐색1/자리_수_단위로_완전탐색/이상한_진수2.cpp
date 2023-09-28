#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

// 2진법 -> 10진법 변환
int convert(string s) {
	int total = 0;
	for (int i = 0; i < s.length(); i++) {
		int n = s[i] - '0';
		total = total * 2 + n;
	}

	return total;
}

// s에서 i인덱스 값 바꿔서 십진법으로 리턴
int solve(string s, int i) {
	char c = s[i];
	if (c == '0') s[i] = '1';
	else s[i] = '0';

	return convert(s);
}

int main() {

	string s;
	cin >> s;

	// 완전탐색
	int maxValue = 0;
	for (int i = 0; i < s.length(); i++) {
		int ret = solve(s, i);
		maxValue = max(maxValue, ret);
	}

	cout << maxValue;
}