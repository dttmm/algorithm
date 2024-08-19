#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 500

int N;
int K;
int arr[MAX_N + 1][MAX_N + 1];
int sum[MAX_N + 1][MAX_N + 1];

// (a+1, b+1) ~ (c, d) ±¸°£ ÇÕ
int getSum(int a, int b, int c, int d) {
	return sum[c][d] - sum[a][d] - sum[c][b] + sum[a][b];
}

int main() {

	cin >> N >> K;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> arr[i][j];

			sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + arr[i][j];
		}
	}

	int maxVal = 0;
	for (int i = K; i <= N; i++) {
		for (int j = K; j <= N; j++) {
			int ret = getSum(i - K, j - K, i, j);

			maxVal = ret > maxVal ? ret : maxVal;
		}
	}

	cout << maxVal;
}