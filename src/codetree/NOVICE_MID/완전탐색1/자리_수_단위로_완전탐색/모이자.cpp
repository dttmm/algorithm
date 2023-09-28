#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int arr[MAX_N];

int solve() {
	int minSum = INT_MAX;

	for (int i = 0; i < N; i++) {
		int sum = 0;
		for (int j = 0; j < N; j++) {
			if (i == j) continue;

			sum += abs(i - j) * arr[j];
		}

		minSum = min(minSum, sum);
	}

	return minSum;
}

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ret = solve();

	cout << ret;
}