#include <iostream>
#include <string>

using namespace std;

int main() {

	string s;
	cin >> s;   // '('의 개수

	int cnt = 0;
	int ans = 0;
	for (int i = 0; i < s.length(); i++) {
		char c = s[i];

		// '('인 경우
		if (c == '(') cnt++;
		// ')'인 경우
		else ans += cnt;
	}

	cout << ans;
}