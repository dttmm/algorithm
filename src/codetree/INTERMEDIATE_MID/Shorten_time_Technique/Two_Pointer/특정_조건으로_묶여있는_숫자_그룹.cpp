#include <bits/stdc++.h>

using namespace std;

#define MAX_N 50000

int N, K;
int arr[MAX_N];
int d[MAX_N];	// i까지 묶을 수 있는 그룹의 최대 길이
int L[MAX_N];	// 0~i까지 그룹의 최대 길이의 최대값

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 정렬
	sort(arr, arr + N);

	// d배열 세팅
	int s = 0;
	int e = 0;
	while (e < N) {
		int diff = arr[e] - arr[s];
		if (diff > K) {
			s++;
		}
		else {
			d[e] = e - s + 1;
			e++;
		}
	}

	// L배열 세팅
	L[0] = d[0];
	for (int i = 1; i < N; i++) {
		L[i] = max(d[i], L[i - 1]);
	}

	// i까지 묶을 수 있는 그룹의 최대 길이 + 그 전에 묶을 수 있는 그룹의 최대 길이
	int ans = 0;
	for (int i = 0; i < N; i++) {
		if (i - d[i] >= 0) {
			ans = max(ans, d[i] + L[i - d[i]]);
		}
	}

	cout << ans;
}