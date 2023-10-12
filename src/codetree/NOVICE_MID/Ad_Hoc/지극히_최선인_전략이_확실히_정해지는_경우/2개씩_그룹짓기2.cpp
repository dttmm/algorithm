#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define INF 1000000000

int N;
int arr[MAX_N * 2];

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < 2 * N; i++) {
		cin >> arr[i];
	}

	// 정렬
	sort(arr, arr + N * 2);

	// i번째 수와 i+N번째 수 차이의 최대값 구하기
	int minVal = INF;
	for (int i = 0; i < N; i++) {
		int diff = arr[N + i] - arr[i];
		minVal = min(minVal, diff);
	}

	cout << minVal;
}