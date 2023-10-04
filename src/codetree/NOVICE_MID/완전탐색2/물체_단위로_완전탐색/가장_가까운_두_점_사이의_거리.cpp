#include <iostream>
#include <algorithm>
#include <climits>
#include <cmath>

using namespace std;

#define MAX_N 100
#define R 2

int N;
int tr[R];
int xArr[MAX_N];
int yArr[MAX_N];
int ans = INT_MAX;

// 조합
void solve(int k, int start) {
	if (k == R) {
		int n1 = tr[0];
		int n2 = tr[1];
		int diff = pow(xArr[n1] - xArr[n2], 2) + pow(yArr[n1] - yArr[n2], 2);

		ans = min(ans, diff);
	}
	else {
		for (int i = start; i < N; i++) {
			tr[k] = i;
			solve(k + 1, i + 1);
		}
	}
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x;
		int y;
		cin >> x >> y;

		xArr[i] = x;
		yArr[i] = y;
	}

	// 2개 뽑기
	solve(0, 0);

	cout << ans;
}