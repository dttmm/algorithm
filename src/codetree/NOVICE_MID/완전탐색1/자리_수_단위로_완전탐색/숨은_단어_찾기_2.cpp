#include <iostream>
#include <string>

using namespace std;

#define MAX_N 50

int N;
int M;
char arr[MAX_N][MAX_N];
int di[] = { -1,-1,0,1,1,1,0,-1 };
int dj[] = { 0,1,1,1,0,-1,-1,-1 };
int ans;

// 범위 체크
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < M);
}

// EE있는지 검사
void solve(int start_i, int start_j) {
	// 8방향 체크
	for (int dir = 0; dir < 8; dir++) {
		int i = start_i;
		int j = start_j;
		int n = 2;

		// dir 방향으로 두번 연속 E가 있는지 검사
		while (n > 0) {
			int newI = i + di[dir];
			int newJ = j + dj[dir];

			// 범위 벗어난 경우
			if (!isIn(newI, newJ)) break;
			// E가 아닌 경우
			if (arr[newI][newJ] != 'E') break;

			// 현재 위치 갱신
			i = newI;
			j = newJ;
			n--;
		}

		// EE를 찾은 경우
		if (n == 0) ans++;
	}
}

int main() {

	// 입력 받기
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;
		for (int j = 0; j < M; j++) {
			char c;
			c = s[j];

			arr[i][j] = c;
		}
	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			// L이 아닌 경우
			if (arr[i][j] != 'L') continue;

			// L위치에서부터 EE찾기
			solve(i, j);
		}
	}

	cout << ans;
}