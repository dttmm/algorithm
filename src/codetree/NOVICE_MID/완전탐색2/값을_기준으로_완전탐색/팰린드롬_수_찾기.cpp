#include <iostream>
#include <string>

using namespace std;

int X;
int Y;
int ans;

// �縰������� �Ǻ�
bool isF(int n) {
	string s = to_string(n);

	for (int i = 0; i < s.length() / 2; i++) {
		if (s[i] != s[s.length() - 1 - i]) return false;
	}
	return true;
}

int main() {

	//�Է�
	cin >> X >> Y;

	// X���� Y���� �˻�
	for (int n = X; n <= Y; n++) {
		bool ret = isF(n);
		if (ret) ans++;
	}

	cout << ans;
}