#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 1분 구현 4분 다시구현 11분
* dp
* 처음에 문제를 잘못 봄
* M원을 만들 수 있는 최대 동전개수로 생각하고
* 동전을 하나씩 사용해 보면서
* m원을 만들 때 사용하는 동전의 최대값 업데이트 해나감
* 
* 맙소사
* 구한 답이 정답과 너무 동떨어져 있는 것을 보고
* 문제를 다시 보고 다시 구현함
* 
* 문제 다시 봤는데도 또 문제 잘못 이해함
* M원을 만들 수 있는 모든 경우의 수를 구하는 건줄 알았음
* 예를 들어 1,2원을 가지고 3원을 만드는데에
* 1두개, 1하나 2하나 사용해서 만드는 것이 정답인데
* 11, 12, 21 총 3개로 경우의 수 찾는 건줄 알고 잘못 이해하고
* m원을 만들기 위해 m원마다 모든 동전을 사용해보면서
* 가능한 경우의 수를 찾도록 구현함;;
* 
* 문제 제대로 이해하고 다시 구현
* 동전 하나씩 사용해보면서
* m원을 만들 수 있는 경우의 수를 더해줌
*/

using namespace std;

#define MAX_N 20
#define MAX_M 10000

int N;
int M;
int arr[MAX_N];
int d[MAX_M + 1];

// dp 배열 초기화
void init() {
	fill(d, d + MAX_M + 1, 0);
}

// dp
void solve() {
	// 초기값 세팅
	d[0] = 1;

	// 동전 i까지 사용했을 때
	for (int i = 0; i < N; i++) {
		int n = arr[i];
		
		// m원을 만들 수 있는 동전의 수 업데이트
		for (int m = 1; m <= M; m++) {
			if (n > m) continue;

			d[m] += d[m - n];
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/9084.txt", "r", stdin);

	// 테케
	int T;
	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		// 입력
		cin >> N;
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}
		cin >> M;

		init();

		solve();

		cout << d[M] << "\n";
	}
}