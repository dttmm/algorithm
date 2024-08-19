#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000

int N;
int K;
int arr[MAX_N];

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + N);

	int s = 0;
	int e = N - 1;
	long long ans = 0;
	while (s < e) {
		if (arr[s] + arr[e] > K) {
			e--;
		}
		else {
			ans += e - s;
			s++;
		}
	}
	cout << ans;
}