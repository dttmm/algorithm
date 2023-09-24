#include <iostream>
#include <fstream>

/*
* 설계 1분 구현 3분 디버깅 5분
* 누적합
* 간단한 누적합 문제
* 
* 시간초과
* N이 크므로
* 분명 C++입출력과 관련이 있을 것 같아서 찾아봄
* 1. 출력할 때 endl보다 \n이 빠르다고 함
* 2. cin, cout의 속도를 빠르게 해줄 수 있음
* 1, 2번 모두를 만족했을 때 시간초과가 안났지만
* 하나라도 만족하지 않으니까 시간초과나네
* 앞으로는 위 두가지 무조건 해줘야 겠음
*/

#define MAX_R 100000

using namespace std;

int N;
int M;
int arr[MAX_R + 1];
int sum[MAX_R + 1];	// 누적합

// 누적합 구하기
void setSum() {
	for (int i = 1; i <= N; i++) {
		sum[i] = sum[i - 1] + arr[i];
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	freopen("res/baekjoon/11441.txt", "r", stdin);

	// 입력 받기
	cin >> N;
	for (int k = 1; k <= N; k++) {
		cin >> arr[k];
	}

	cin >> M;

	// 누적합 구하기
	setSum();

	// 쿼리 결과 구하기
	for (int k = 0; k < M; k++) {
		int s;
		int e;
		cin >> s >> e;

		int ret = sum[e] - sum[s - 1];

		cout << ret << "\n";
	}
}