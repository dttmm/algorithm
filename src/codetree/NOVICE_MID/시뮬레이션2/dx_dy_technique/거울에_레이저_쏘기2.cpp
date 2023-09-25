#include <iostream>
#include <fstream>

using namespace std;

#define MAX_N 1000

int N;
int K;
char arr[MAX_N][MAX_N];
int i;
int j;
int di[] = { 1,0,-1,0 };
int dj[] = { 0,-1,0,1 };

// 범위 검사
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

// 시작 지점 정하기
void setStart() {
	i = 0;
	j = -1;
	int dir = 3;
	int k = K;

	// 테두리를 돌면서 시작 지점 i, j 구하기
	while (k > 0) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어난 경우 -> 90도 회전
		if (!isIn(newI, newJ)) dir = (dir + 1) % 4;
		// 갈수 있는 경우 -> 이동
		else {
			i = i + di[dir];
			j = j + dj[dir];
		}
		k--;
	}
}

// 이동하기
int solve() {

	int dir = K / N;	// 초기 방향 구하기
	i = i - di[dir];	// 초기 위치 세팅
	j = j - dj[dir];	// 초기 위치 세팅

	// 거울에 튕긴 횟수
	int cnt = 0;

	// 범위를 벗어날 때까지 이동
	while (true) {
		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위를 벗어난 경우
		if (!isIn(newI, newJ)) break;

		i = newI;
		j = newJ;
		cnt++;

		// /모양 거울을 만난 경우
		if (arr[newI][newJ] == '/') {
			// 위아래 방향일 때
			if (dir % 2 == 0) dir = (dir + 1) % 4;
			// 좌우 방향일 때
			else dir = (dir + 3) % 4;
		}
		// \모양 거울을 만난 경우
		else {
			// 위아래 방향일 때
			if (dir % 2 == 0) dir = (dir + 3) % 4;
			// 좌우 방향일 때
			else dir = (dir + 1) % 4;
		}
	}

	return cnt;
}

int main() {

	freopen("res/1.txt", "r", stdin);

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < N; j++) {
			char c = s[j];

			arr[i][j] = c;
		}
	}

	cin >> K;

	// 시작 지점 구하기
	setStart();

	// 정답 구하기
	int ret = solve();

	cout << ret;
}