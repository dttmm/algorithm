#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 1000

int N;
int K;
int arr[MAX_N + 1];
int sum[MAX_N + 1];

int main() {

	cin >> N >> K;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];

		sum[i] = sum[i - 1] + arr[i];
	}

	int L = 0;
	int R = 1;

	int ans = 0;
	while (R <= N) {
		int diff = sum[R] - sum[L];

		if (diff < K) R++;
		else if (diff > K) L++;
		else {
			ans++;
			R++;
			L++;
		}

		if (R == L) R++;
	}

	cout << ans;
}