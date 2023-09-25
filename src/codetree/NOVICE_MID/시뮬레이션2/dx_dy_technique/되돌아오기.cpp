#include <iostream>

using namespace std;

#define MAX_N 100
#define MAX_ASCII 128

int N;
int mapper[MAX_ASCII];
int cnt;
int i;
int j;
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

void setMapper() {
	mapper['N'] = 0;
	mapper['E'] = 1;
	mapper['W'] = 2;
	mapper['S'] = 3;
}

// x초동안 dir로 움직이기
bool move(int x, int dir) {
	while (x > 0) {
		x--;
		cnt++;

		int newI = i + di[dir];
		int newJ = j + dj[dir];

		if (newI == 0 && newJ == 0) return true;

		i = newI;
		j = newJ;
	}

	return false;
}

int main() {

	cin >> N;

	setMapper();

	bool flag = false;
	for (int k = 0; k < N; k++) {
		char c;
		int x;
		cin >> c >> x;
		int dir = mapper[c];

		bool ret = move(x, dir);
		if (ret) {
			flag = true;
			break;
		}
	}

	if (flag) cout << cnt;
	else cout << -1;
}