#include <iostream>
#include <string>

using namespace std;

int main() {

	string s;
	cin >> s;   // '('�� ����

	int cnt = 0;
	int ans = 0;
	for (int i = 0; i < s.length(); i++) {
		char c = s[i];

		// '('�� ���
		if (c == '(') cnt++;
		// ')'�� ���
		else ans += cnt;
	}

	cout << ans;
}