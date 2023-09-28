#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100

int N;
int T;
int H;
int arr[MAX_N];	// H와의 높이 차이 저장

int main() {

	cin >> N >> H >> T;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		// H와의 높이 차이 (H가 되기 위해 필요한 값)
		int diff = abs(H - n);
		arr[i] = diff;
	}

	int sum = 0;
	int ans = INT_MAX;
	// arr에서 연속적으로 T개를 골랐을 때 최소값을 구해주면 됨
	for (int i = 0; i < N; i++) {
		// T개를 아직 못 골랐을 때
		if (i < T) {
			sum += arr[i];

			// T개 골랐을 때
			if (i == T - 1) ans = min(ans, sum);
			continue;
		}

		// 현재 값은 더해주고 이전에 더해준 값은 빼줌
		sum += arr[i];
		sum -= arr[i - T];
		ans = min(ans, sum);
	}

	cout << ans;
}