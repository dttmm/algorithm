#include <iostream>
#include<string>

using namespace std;

#define MAX_N 1000

int N;
int M;
int K;
int arr[MAX_N + 1][MAX_N + 1][3];
int sum[MAX_N + 1][MAX_N + 1][3];

void initSum() {
	for (int x = 0; x < 3; x++) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				sum[i][j][x] = sum[i - 1][j][x] + sum[i][j - 1][x] - sum[i - 1][j - 1][x] + arr[i][j][x];
			}
		}
	}
}

void getSum(int a, int b, int c, int d) {
	for (int x = 0; x < 3; x++) {
		int cnt = sum[c][d][x] - sum[a - 1][d][x] - sum[c][b - 1][x] + sum[a - 1][b - 1][x];
		cout << cnt << " ";
	}
	cout << "\n";
}

int main() {

	cin >> N >> M >> K;
	for (int i = 1; i <= N; i++) {
		string s;
		cin >> s;

		for (int j = 1; j <= M; j++) {
			char c = s[j - 1];
			arr[i][j][c - 'a'] = 1;
		}
	}

	initSum();

	for (int k = 0; k < K; k++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;

		getSum(a, b, c, d);
	}
}