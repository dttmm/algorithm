#include <bits/stdc++.h>

using namespace std;

#define MAX_N 100000

int N;
int M;
int arr[MAX_N];
unordered_map<int, int> mapOut;	// 범위 밖에 있는 수 정보
unordered_map<int, int> mapIn;	// 범위 안에 있는 수 정보

int main() {

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		mapOut[arr[i]]++;
	}

	int ans = MAX_N * 2;
	int e = 0;
	for (int s = 0; s < N; s++) {
		while (e < N && mapOut.size() == M) {
			if (mapIn.size() == M) {
				ans = min(ans, e - s);
				break;
			}

			int n = arr[e];
			mapOut[n]--;
			mapIn[n]++;
			if (mapOut[n] == 0) mapOut.erase(n);

			e++;
		}

		mapOut[arr[s]]++;
		mapIn[arr[s]]--;
		if (mapIn[arr[s]] == 0) mapIn.erase(arr[s]);
	}

	if (ans == MAX_N * 2) ans = -1;
	cout << ans;
}