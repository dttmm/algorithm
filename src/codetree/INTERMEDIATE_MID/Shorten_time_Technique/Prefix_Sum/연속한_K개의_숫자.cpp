#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 100000

int N;
int K;
int B;
int arr[MAX_N + 1];	// 1: i자리에 숫자가 없음
int sum[MAX_N + 1]; // 현재위치까지 없는 숫자 개수

int main() {

	cin >> N >> K >> B;
	for (int i = 0; i < B; i++) {
		int n;
		cin >> n;

		// 없는 숫자 표시
		arr[n] = 1;
	}

	// 누적합
	for (int i = 1; i <= N; i++) {
		sum[i] = sum[i - 1] + arr[i];
	}

	// 현재위치에서 연속된 K개 골랐을 때 없는 숫자 개수의 최소값 구함
	int min = MAX_N;
	for (int i = K; i <= N; i++) {
		int cnt = sum[i] - sum[i - K];
		min = cnt < min ? cnt : min;
	}

	cout << min;
}