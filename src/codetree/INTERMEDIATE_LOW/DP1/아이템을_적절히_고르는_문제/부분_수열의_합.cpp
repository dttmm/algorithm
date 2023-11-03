#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_M 10000

int N;
int M;
int arr[MAX_N];
bool d[MAX_M + 1];	// 부분 수열로 m을 만들 수 있는지 없는지

// dp
void solve() {
	d[0] = true;

	// 원소 하나 n골라서
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// n을 이용해서 m을 만들수 있는지 검사
		for (int m = M; m >= 1; m--) {
			if (n > m) continue;
			if (!d[m - n]) continue;

			d[m] = true;
		}
	}
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	solve();

	// M을 만들수 있는 경우
	if (d[M]) cout << "Yes";
	// 없는 경우
	else cout << "No";
}