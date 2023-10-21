#include <iostream>
#include <fstream>

/*
* 설계 4분 구현 4분 디버깅 7+12분
* dp
* 이차원 dp를 이용하여
* N자리 j로 끝나는 수 일 때
* 오르막 수를 저장함
* 예를 들어 2자리 2로 끝나는 오르막 수는
* 1자리 1으로 끝나는 오르막 수 + 1자리 2로 끝나는 오르막 수이므로
* 점화식을
* d[i][j] = d[i-1][0] + ... + d[i-1][j]로 세움
* 
* 답이 안나옴
* 이상해서 질문 게시판 보는데
* 문제 조건이
* 수는 0으로 시작할 수 "있다"였음
* "없다"인줄 알고 0은 고려 안했었는데
* 다행히 나처럼 노안인 사람들이 많나봄
* 
* 틀림
* 분명 다른 사람이 올린 테케 다 맞았는데..
* 아 dp배열 크기 MAX_N+1이 아니라 MAX_N로 해버려서 틀림
*/

using namespace std;

#define MAX_N 1000
#define MOD 10007

int N;
int d[MAX_N + 1][10];	// N자리수 j로 끝나는 수의 오르막 수 개수

// dp배열 세팅
void setD() {

	// 1의 자리는 가능한 경우의 수 1로 초기화
	for (int i = 0; i < 10; i++) {
		d[1][i] = 1;
	}

	// 2의 자리부터 값 채워줌
	for (int i = 2; i <= N; i++) {

		// i자리 j로 끝나는 오르막 수 개수 구해주기
		for (int j = 0; j < 10; j++) {

			// 이전 자리수의 j보다 작은 수로 끝나는 오르막 수 더해줌
			int sum = 0;
			for (int x = 0; x <= j; x++) {
				sum = (sum + d[i - 1][x]) % MOD;
			}

			d[i][j] = sum;
		}
	}
}

int main() {

	freopen("res/baekjoon/11057.txt", "r", stdin);

	// 입력
	cin >> N;

	// dp 배열 세팅
	setD();

	// N자리수의 오르막 수 구하기
	int ans = 0;
	for (int j = 0; j < 10; j++) {
		ans = (ans + d[N][j]) % MOD;
	}

	cout << ans;
}