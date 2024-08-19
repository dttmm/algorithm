#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000

int N;
int K;
int arr[MAX_N];
unordered_map<int, int> Map;

int main() {

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ans = 0;
	int e = 0;
	for (int s = 0; s < N; s++) {
		while (e < N && Map[arr[e]] < K) {
			Map[arr[e]]++;
			ans = max(ans, e - s + 1);
			e++;
		}
		Map[arr[s]]--;
	}

	cout << ans;
}