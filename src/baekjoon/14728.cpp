#include <iostream>
#include <fstream>
#include <algorithm>

/*
* 설계 3분 구현 6분 디버깅 8분
* dp
* 전형적인 dp문제
* 과목을 하나씩 선택해가며
* 해당 과목까지 선택을 했을 때 최대값을 구해나감
* 
* 틀림
* 과목을 선택했을 때와 선택하지 않았을 때(대각선)만 고려함
* 위, 옆 방향에서 오는 최대값도 고려해줘야됨
*/
using namespace std;

#define MAX_N 100
#define MAX_T 10000

int N;
int T;
int krr[MAX_N + 1];	// i과목의 공부 시간
int srr[MAX_N + 1];	// i과목의 배점
int d[MAX_N + 1][MAX_T + 1];	// i과목까지 선택하고 j시간이 주어졌을 때 최대값

int main() {

	freopen("res/baekjoon/14728.txt", "r", stdin);

	// 입력 받기
	cin >> N >> T;
	for (int i = 1; i <= N; i++) {
		int k;
		int s;
		cin >> k >> s;

		krr[i] = k;
		srr[i] = s;
	}

	// 이차원 dp
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= T; j++) {
			int k = krr[i];
			int s = srr[i];

			// 위, 옆 최대값 비교
			d[i][j] = max(d[i][j], d[i][j - 1]);
			d[i][j] = max(d[i][j], d[i - 1][j]);

			// i과목을 선택하지 않았을 때와 선택했을 때 최대값 비교
			if (j >= k) d[i][j] = max(d[i][j], d[i - 1][j - k] + s);
		}
	}

	cout << d[N][T];
}