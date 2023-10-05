#include <iostream>
#include <string>

using namespace std;

int X;
int Y;
int ans;

// 펠린드롭인지 판별
bool isF(int n) {
	string s = to_string(n);

	for (int i = 0; i < s.length() / 2; i++) {
		if (s[i] != s[s.length() - 1 - i]) return false;
	}
	return true;
}

int main() {

	//입력
	cin >> X >> Y;

	// X부터 Y까지 검사
	for (int n = X; n <= Y; n++) {
		bool ret = isF(n);
		if (ret) ans++;
	}

	cout << ans;
}