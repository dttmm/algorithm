#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1003

int N;
int arr[MAX_N];

// k방을 기준으로 했을 때 결과
int solve(int k) {
	int sum = 0;
	int d = 1;	//  k방에서 다른 방까지의 거리
	int n = N - 1;	// 남은 반복 횟수

	// 처음밤을 제외하고
	// n-1번 반복
	while (n > 0) {
		int index = (k + d) % N;	// 해당 방의 arr에서 인덱스
		sum += arr[index] * d;

		d++;
		n--;
	}

	return sum;
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 완전탐색
	int minValue = INT_MAX;
	for (int i = 0; i < N; i++) {

		int ret = solve(i);

		minValue = min(minValue, ret);
	}

	cout << minValue;
}