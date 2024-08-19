#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int Q;
int arr[MAX_N + 1];
int sum[MAX_N + 1][4];

void init() {
	for (int x = 1; x <= 3; x++) {
		for (int i = 1; i <= N; i++) {
			sum[i][x] = sum[i - 1][x] + (arr[i] == x);
		}
	}
}

void getSum(int i, int j) {
	for (int x = 1; x <= 3; x++) {
		int cnt = sum[j][x] - sum[i - 1][x];
		cout << cnt << " ";
	}
	cout << "\n";
}

int main() {

	cin >> N >> Q;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	init();

	for (int q = 0; q < Q; q++) {
		int i, j;
		cin >> i >> j;

		getSum(i, j);
	}
}