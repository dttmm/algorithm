#include <iostream>
#include <algorithm>
#include <string>
#include <fstream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N][MAX_N];
int di[] = { 0,-1,0,1 };
int dj[] = { -1,0,1,0 };

// 범위 체크
bool isIn(int i, int j) {
	return(i >= 0 && i < N&& j >= 0 && j < N);
}

// 배열돌리기
void solve() {
	int i = N - 1;
	int j = N - 1;
	int dir = 0;
	int num = N * N;

	arr[i][j] = num--;

	while (num > 0) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어나거나 이미 방문한 경우
		if (!isIn(newI, newJ) || arr[newI][newJ] != 0) {
			dir = (dir + 1) % 4;
			newI = i + di[dir];
			newJ = j + dj[dir];
		}

		i = newI;
		j = newJ;
		arr[i][j] = num--;
	}
}

int main() {

	cin >> N;

	solve();

	// 출력
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}
}