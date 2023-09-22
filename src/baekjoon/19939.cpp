#include <iostream>
#include <fstream>

/*
* 설계 21분 구현 4분
* 수학
* 일단 단순하게 N과 K가 주어졌을 때
* 바로 답을 구할 수 있는 방법을 찾아봄
* 일단 N이 K * (K + 1) / 2보다 작으면
* 공을 모두 다르게 나눠줄 수가 없음
* 
* 그래서 공을 모두 다르게 나눠줄 수 있는 경우
* 정답을 바로 구할 수 있는 방법을 찾기 위해 노력함
* 우선 공을 1개, 2개, 3개...
* 점점 하나씩 늘려가면서 나눠주고
* 공이 남으면 남은 공을 뒤쪽 사람에게 몰빵해줬는데
* 공을 2개, 3개, 4개... 이렇게
* 꼭 앞 사람이 1개를 받지 않아도 되는 경우가 생김
* 
* 그래서 K룰 고정하고 N을 늘려가면서
* 정답을 일일이 찾아보면서 패턴을 찾으려고함
* 그랬더니 N이 K로 나눠지는 경우 정답이 K-1이 되고
* N이 K로 나눠지지 않는 경우에는 정답이 K가 되는 패턴을 발견
*/

using namespace std;

int N;
int K;
int standard;	// N의 최소 조건 K * (K + 1) / 2

// N의 최소 조건 구하기
int getStandard() {
	return K * (K + 1) / 2;
}

// 정답 구하기
int solve() {
	// N이 최소 조건을 넘지 못하는 경우 -> 실패
	if (N < standard) return -1;

	// N이 K로 나누어 떨어지는 경우
	int rest = (N - standard) % K;
	// N이 K로 나누어 떨어지지 않는 경우
	if (rest == 0) return K - 1;
	else return K;
}

int main() {

	freopen("res/baekjoon/19939.txt", "r", stdin);
	cin >> N >> K;

	// 기준 구하기
	standard = getStandard();

	// 정답 구하기
	int ret = solve();

	cout << ret;
}