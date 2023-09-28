#include <iostream>
#include <string>

using namespace std;

int main() {

	string s;
	cin >> s;

	int N = s.length();
	int cnt = 0;	// 현재까지 나온 '((' 개수
	int ans = 0;
	for (int i = 1; i < N; i++) {
		if (s[i] == '(' && s[i - 1] == '(') cnt++;
		else if (s[i] == ')' && s[i - 1] == ')') ans += cnt;
	}

	cout << ans;
}