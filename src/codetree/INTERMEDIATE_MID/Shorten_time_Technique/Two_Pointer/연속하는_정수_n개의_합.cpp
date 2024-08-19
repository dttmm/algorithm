#include <iostream>

using namespace std;

#define MAX_N 100000

int N;
int K;
int arr[MAX_N];

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;
	int sum = 0;
	int R = 0;
	for (int L = 0; L < N; L++) {
		while (R < N && sum < K) {
			sum += arr[R];
			R++;
		}

		if (sum == K) ans++;

		sum -= arr[L];
	}

	cout << ans;
}