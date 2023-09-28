#include <iostream>
#include <string>

using namespace std;

#define MAX_N 99

int N;
int T;
string s;
int arr[MAX_N][MAX_N];
int di[] = { -1,0,1,0 };
int dj[] = { 0,1,0,-1 };

bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

int solve() {
	int i = N / 2;
	int j = N / 2;
	int dir = 0;
	int sum = arr[i][j];

	for (int k = 0; k < s.length(); k++) {
		char c = s[k];

		if (c == 'R') {
			dir = (dir + 1) % 4;
		}
		else if (c == 'L') {
			dir = (dir + 3) % 4;
		}
		else {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			if (!isIn(newI, newJ)) continue;

			i = newI;
			j = newJ;
			sum += arr[i][j];
		}
	}

	return sum;
}

int main() {

	cin >> N >> T;
	cin >> s;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	int ret = solve();

	cout << ret;
}