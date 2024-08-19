#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000
#define MAX_K 2000000
#define MAX_C 1000000
#define MAX(a, b) ((a) > (b)) ? (a) : (b)

int N;
int K;
int arr[MAX_C + 1];

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int cnt, x;
		cin >> cnt >> x;
		arr[x] += cnt;
	}

	int total = 0;

	// 왼쪽에서 K-1개는 미리 선택
	for (int k = 0; k < K; k++) {
		if (k > MAX_C) break;
		total += arr[k];
	}
	int maxVal = total;

	// 중심점을 옮겨봄
	for (int c = 0; c <= MAX_C; c++) {
		if (c - K - 1 >= 0) total -= arr[c - K - 1];
		if (c + K <= MAX_C) total += arr[c + K];
		maxVal = MAX(maxVal, total);
	}

	cout << maxVal;
}