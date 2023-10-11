#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];
int brr[MAX_N];

int solve() {
	int cost = 0;
	// 현재 집에서 남는 사람 옆집으로 이동
	for (int i = 0; i < N - 1; i++) {
		int rest = arr[i] - brr[i];	// 남는 사람
		cost += rest;				// 이동 비용
		arr[i + 1] += rest;			// 옆집 인원수 증가
	}

	return cost;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	for (int i = 0; i < N; i++) {
		cin >> brr[i];
	}

	int ret = solve();
	cout << ret;
}