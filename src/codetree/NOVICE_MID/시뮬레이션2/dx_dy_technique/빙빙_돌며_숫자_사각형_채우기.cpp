#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];
int di[] = { 0,1,0,-1 };
int dj[] = { 1,0,-1,0 };

bool isIn(int i, int j) {
	return(i >= 0 && i < N&& j >= 0 && j < M);
}

void solve() {
	int i = 0;
	int j = 0;
	int dir = 0;
	arr[0][0] = 1;

	for (int k = 2; k <= N * M; k++) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위를 벗어나거나 이미 방문했으면
		if (!isIn(newI, newJ) || arr[newI][newJ] != 0) dir = (dir + 1) % 4;

		newI = i + di[dir];
		newJ = j + dj[dir];

		i = newI;
		j = newJ;
		arr[i][j] = k;
	}
}

int main() {

	cin >> N >> M;

	solve();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}
}