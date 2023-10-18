#include <fstream>
#include <iostream>

/*
* 설계 4분 구현 3분
* dp
* N자리수는 N-1자리수 뒤에 0이나 1을 붙여서 만들 수 있음
* 0과 1을 붙을 수 있는 조건을 따져봄
* N-1자리수의 마지막 숫자가 0이거나 1이거나 상관없이 뒤에 0을 붙일 수 있고
* N-1자리수의 마지막 숫자가 0이면 뒤에 1을 붙일 수 있지만, 1이면 뒤에 1을 붙일 수 없음
* 이를 dp 배열을 이용하여 점화식을 세워서 품
* N이 커질수록 가능한 경우의 수가 int자료형을 넘어가지에 long long형 사용함
*/

using namespace std;

#define MAX_N 90

int N;
long long d[MAX_N + 1][2];	// N자리수에서 j로 끝나는 수 개수

// dp배열 세팅
void setD() {
	d[1][1] = 1;	// 1자리 수는 1만 가능

	// 2자리 수부터 바텀업
	for (int i = 2; i <= N; i++) {
		// 뒤에 0을 붙일 수 있는 경우
		d[i][0] = d[i - 1][0] + d[i - 1][1];
		// 뒤에 1을 붙일 수 있는 경우
		d[i][1] = d[i - 1][0];
	}
}

int main() {

	freopen("res/baekjoon/2193.txt", "r", stdin);

	// 입력
	cin >> N;

	// dp 세팅
	setD();

	// 정답 구하기
	long long ans = d[N][0] + d[N][1];
	cout << ans;
}