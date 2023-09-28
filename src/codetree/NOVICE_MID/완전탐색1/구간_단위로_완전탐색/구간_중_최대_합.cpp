#include <iostream>
#include <cmath>

using namespace std;

#define MAX_N 100

int N;
int K;
int arr[MAX_N];

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;
	// N-K까지 포함해야 되는 것 조심
	for (int i = 0; i <= N - K; i++) {
		int sum = 0;
		for (int j = i; j < i + K; j++) {
			sum += arr[j];
		}
		ans = max(ans, sum);
	}

	cout << ans;
}