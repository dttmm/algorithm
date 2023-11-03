#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int d1[MAX_N];	// 왼쪽에서부터 증가 부분 수열 개수 최대값
int d2[MAX_N];	// 오른쪽에서부터 증가 부분 수열 개수 최대값

// dp
void solve() {
	// 초기화
	for (int i = 0; i < N; i++) {
		d1[i] = 1;
		d2[i] = 1;
	}

	// 왼쪽에서부터 증가 부분 수열 개수 최대값 구하기
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < i; j++) {
			if (arr[j] >= arr[i]) continue;
			d1[i] = max(d1[i], d1[j] + 1);
		}
	}

	// 오른쪽에서부터 증가 부분 수열 개수 최대값 구하기
	for (int i = N - 1; i >= 0; i--) {
		for (int j = N - 1; j > i; j--) {
			if (arr[j] >= arr[i]) continue;
			d2[i] = max(d2[i], d2[j] + 1);
		}
	}
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// 기준 i를 중심으로
	// 왼쪽에서는 i까지의 증가 부분 수열의 길이
	// 오른쪽에서는 i부터 시작하는 증가 부분 수열의 길이
	// 둘의 합 중 최대값 구함
	int ans = 0;
	for (int i = 0; i < N; i++) {
		ans = max(ans, d1[i] + d2[i]);
	}

	// 기준 i가 중복으로 카운트돼서 하나 빼줌
	cout << ans - 1;
}