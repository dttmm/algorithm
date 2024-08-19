#include <bits/stdc++.h>

using namespace std;

#define MAX_N 1000000
#define MIN(a, b) ((a) < (b)) ? (a) : (b);

int N;
int K;
int arr[MAX_N];

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 2 * MAX_N;
	int cnt = 0;
	int R = 0;
	for (int L = 0; L < N; L++) {
		while (R < N && cnt != K) {
			if (arr[R] == 1) cnt++;
			R++;
		}

		if (cnt == K) ans = MIN(ans, R - L);

		if (arr[L] == 1) cnt--;
	}

	if (ans == 2 * MAX_N) ans = -1;
	cout << ans;
}