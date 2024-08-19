#include <iostream>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int arr[MAX_N + 1];
int d[MAX_N + 1][4];	// i인덱스까지 w개를 선택했을 때 최대값

void init() {
	// dp 배열 초기화
	for (int i = 0; i <= N; i++) {
		for (int w = 0; w <= 3; w++) {
			d[i][w] = -1;
		}
		d[i][0] = 0;	// 0개 선택하는 경우는 최대값 0
	}
}

void solve() {
	d[1][1] = arr[1];
	for (int i = 2; i <= N; i++) {
		for (int w = 1; w <= 3; w++) {
			// i-2인덱스까지 w-1개 선택 가능한 경우에만 진행
			if (d[i - 2][w - 1] != -1) d[i][w] = MAX(d[i][w], d[i - 2][w - 1] + arr[i]);
			// i-1인덱스까지 w개 선택 가능한 경우에만 진행
			if (d[i - 1][w] != -1) d[i][w] = MAX(d[i][w], d[i - 1][w]);
		}
	}
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	init();

	solve();

	cout << d[N][3];
}