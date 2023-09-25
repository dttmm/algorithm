#include <iostream>

using namespace std;

#define MAX_N 50

int N;
int T;
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };
int arr[MAX_N + 1][MAX_N + 1];
int i;
int j;
int dir;

bool isIn(int i, int j) {
	return (i >= 1 && i <= N && j >= 1 && j <= N);
}

int main() {

	// 입력 받기
	cin >> N >> T;
	cin >> i >> j;

	char c;
	cin >> c;

	if (c == 'U') dir = 0;
	else if (c == 'R') dir = 1;
	else if (c == 'L') dir = 2;
	else  dir = 3;

	// t초 동안 움직이기
	while (T > 0) {
		// 새로운 좌표
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어난 경우
		if (!isIn(newI, newJ)) dir = 3 - dir;
		// 이동할 수 있는 경우
		else {
			i = newI;
			j = newJ;
		}

		T--;
	}

	cout << i << " " << j;
}