#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N + 1][MAX_N + 1];
int di[] = { -1,0,0,1 };
int dj[] = { 0,1,-1,0 };

bool isIn(int i, int j) {
	return (i >= 1 && i <= N && j >= 1 && j <= N);
}

bool solve(int i, int j) {
	arr[i][j] = 1;
	int cnt = 0;

	for (int dir = 0; dir < 4; dir++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		if (!isIn(newI, newJ)) continue;
		if (arr[newI][newJ] == 1) cnt++;
	}

	return cnt == 3;
}

int main() {

	cin >> N >> M;

	for (int k = 0; k < M; k++) {
		int i;
		int j;
		cin >> i >> j;

		bool ret = solve(i, j);
		if (ret) cout << 1 << "\n";
		else cout << 0 << "\n";
	}
}