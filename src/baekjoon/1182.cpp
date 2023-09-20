#include <iostream>
#include <fstream>

/*
* 설계 2분 구현 9분
* 비트마스킹
* 각 원소를 선택했는지 안했는지를 비트마스킹으로 경우의 수 구함
* 수열에서 부분수열을 적어도 하나 이상 골라야하기 때문에
* i는 1부터 시작함
*/

using namespace std;

int N;
int S;
int arr[20];

// 비트마스킹
int solve() {
	int answer = 0;

	for (int i = 1; i < (1 << N); i++) {
		int sum = 0;
		for (int j = 0; j < N; j++) {
			// 고른 숫자인 경우
			if (i & (1 << j)) {
				sum += arr[j];	// 해당 숫자 더해줌
			}
		}

		// S와 일치하는지 확인
		if (sum == S) answer++;

	}

	return answer;
}

int main() {

	freopen("res/baekjoon/1182.txt", "r", stdin);

	cin >> N >> S;

	// 입력 받기
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 비트마스킹
	int ret = solve();

	cout << ret;
}