#include <iostream>
#include <fstream>

/*
* 설계 1분 구현 12분
* 구현
* 코드트리에서 배웠던 스킬 사용함
* 범위를 벗어나거나 이미 방문한 곳인 경우 방향을 회전함
* 1부터 시작하는 것이 아닌 마지막 숫자부터 배열을 회전하며 숫자를 채움
*/
using namespace std;

#define MAX_N 999

int N;
int arr[MAX_N][MAX_N];
int answer[2];	// 찾고자 하는 숫자의 좌표 0: i행 정보, 1: j열 정보
int target;		// 찾고자 하는 숫자
int di[] = { 1,0,-1,0 };
int dj[] = { 0,1,0,-1 };

// 범위 체크
bool isIn(int i, int j) {
	return (i >= 0 && i < N&& j >= 0 && j < N);
}

// 배열 돌리기
void solve() {
	// 0,0 부터 시작
	int i = 0;
	int j = 0;
	int dir = 0;
	int limit = N * N;
	// N*N번 반복하며 숫자 채움
	for (int k = N * N; k > 0; k--) {
		arr[i][j] = k;

		// 찾는 숫자 찾았을 경우
		if (k == target) {
			answer[0] = i + 1;
			answer[1] = j + 1;
		}

		int newI = i + di[dir];
		int newJ = j + dj[dir];

		// 범위 벗어나거나 이미 방문한 경우
		if (!isIn(newI, newJ) || arr[newI][newJ] != 0) {
			dir = (dir + 1) % 4;
			newI = i + di[dir];
			newJ = j + dj[dir];
		}

		// 현재 위치 갱신
		i = newI;
		j = newJ;
	}
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	freopen("res/baekjoon/1913.txt", "r", stdin);

	// 입력 받기
	cin >> N;
	cin >> target;

	// 배열 돌리기
	solve();

	// 출력
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << arr[i][j] << " ";
		}
		cout << "\n";
	}

	cout << answer[0] << " " << answer[1];
}