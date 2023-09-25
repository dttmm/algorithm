#include <iostream>

using namespace std;

string s;
int i;
int j;
int dir;
int cnt;
int di[] = { -1,0,1,0 };
int dj[] = { 0,1,0,-1 };
bool flag;

void solve(char c) {
	cnt++;

	if (c == 'L') {
		dir = (dir + 3) % 4;
	}
	else if (c == 'R') {
		dir = (dir + 1) % 4;
	}
	else {
		int newI = i + di[dir];
		int newJ = j + dj[dir];
		if (newI == 0 && newJ == 0) flag = true;

		i = newI;
		j = newJ;
	}
}

int main() {

	cin >> s;

	for (int i = 0; i < s.length(); i++) {
		char c = s[i];

		solve(c);

		if (flag) break;
	}

	if (flag) cout << cnt;
	else cout << -1;
}