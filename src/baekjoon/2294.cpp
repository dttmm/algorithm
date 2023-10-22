#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 2분 구현 3분 디버깅 1분
* dp
* 모든 동전을 사용해보면서
* 1원부터 K원을 만듦
* 그리고 특정 금액을 만들 수 있는 경우 중에
* 사용한 동전 개수의 최소값을 업데이트 하면서
* dp배열을 채워줌
* 
* 틀림
* 동전을 사용하여 K원을 만들 수 없을 때의
* 예외 처리를 안했음ㅎ
*/

using namespace std;

#define MAX_N 100
#define MAX_K 10000
#define INF 100000000

int N;
int K;
int coins[MAX_N];	// 동전들
int d[MAX_K];		// k원을 만들기 위한 최소한의 동전 개수

// dp배열 채우기
void solve() {
	// 1원 ~ K원까지 만들기 위한 동전의 최소 개수 구하기
	for (int k = 1; k <= K; k++) {
		int minVal = 100000000;

		// k원을 만드는 것이 가능한 동전들 탐색
		for (int i = 0; i < N; i++) {
			int n = coins[i];

			// 해당 동전으로 k원을 만들 수 없는 경우
			if (k - n < 0) continue;

			// 그 중 최소값 선택
			minVal = min(minVal, d[k - n] + 1);
		}

		d[k] = minVal;
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/2294.txt", "r", stdin);

	// 입력
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> coins[i];
	}

	solve();

	// 주어진 동전으로 K원 만들기 불가능한 경우
	if (d[K] == 100000000) cout << -1;
	else cout << d[K];
}