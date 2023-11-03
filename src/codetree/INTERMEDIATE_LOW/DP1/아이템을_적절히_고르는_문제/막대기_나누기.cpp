#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int arr[MAX_N + 1];
int d[MAX_M + 1];

struct Node {
	int n;
	int v;
}nodes[MAX_N];

// dp
void solve() {
	// 길이가 n인 막대를 고려하였을 때
	for (int n = 1; n <= N; n++) {
		int v = arr[n];

		// 길이가 m인 막대를 만들기 위한 최대값 찾음
		for (int m = 1; m <= N; m++) {
			if (n > m) continue;

			d[m] = max(d[m], d[m - n] + v);
		}
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	solve();

	cout << d[N];
}