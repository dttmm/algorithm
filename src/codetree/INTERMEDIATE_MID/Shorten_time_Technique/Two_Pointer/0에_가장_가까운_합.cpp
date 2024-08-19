#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N];

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	sort(arr, arr + N);

	int s = 0;
	int e = N - 1;
	int minVal = INT_MAX;
	while (s < e) {
		int sum = arr[s] + arr[e];

		minVal = min(minVal, abs(sum));
		if (sum > 0) e--;
		else if (sum < 0) s++;
		else break;
	}

	cout << minVal;
}