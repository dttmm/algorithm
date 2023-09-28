#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 100
#define MAX_ALPHA 26

int N;
int M;
char arr[MAX_N][MAX_N];
int di[] = { 0,1,0,-1 };
int dj[] = { 1,0,-1,0 };
int i;
int j;
char crr[MAX_ALPHA];

// 알파벳 배열 초기화
void setCrr() {
	for (int i = 0; i < MAX_ALPHA; i++) {
		crr[i] = 'A' + i;
	}
}

// 범위 체크
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < M);
}

// 배열 채우기
void solve() {
	int cNum = 0;
	int dir = 0;

	// 시작 지점
	arr[i][j] = crr[cNum++];

	while (cNum < N * M) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어나거나 이미 방문한 경우
		if (!isIn(newI, newJ) || arr[newI][newJ] != '\0') {
			dir = (dir + 1) % 4;
			newI = i + di[dir];
			newJ = j + dj[dir];
		}

		i = newI;
		j = newJ;

		arr[i][j] = crr[cNum % MAX_ALPHA];
		cNum++;
	}
}


int main() {

	cin >> N >> M;

	setCrr();

	solve();

	// 출력
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}
}