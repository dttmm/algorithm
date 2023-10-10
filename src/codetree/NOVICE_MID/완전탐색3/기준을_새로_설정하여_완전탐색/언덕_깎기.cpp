#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];
int ans = INT_MAX;

void solve() {
	int minH = arr[0];		// 최소값
	int maxH = arr[N - 1];	// 최대값

	// 최대값 최소값 차이
	int diff = maxH - minH;
	if (diff <= 17) {
		ans = 0;
		return;
	}

	// 둘의 차와 17의 차이
	int range = diff - 17;

	// 최대 최소 차이가 17이내가 될 수 있도록하는
	// 범위(left ~ right)세팅
	// 해당 범위를 넘어가는 높이는 높이를 깎음
	for (int i = 0; i < range; i++) {
		int left = minH + i;
		int right = maxH - range + i;

		// 총 비용
		int sum = 0;
		// left보다 작은 언덕 깎기
		for (int j = 0; j < N; j++) {
			int n = arr[j];

			if (n < left) {
				int diff = left - n;
				sum += diff * diff;
			}
			else break;
		}

		// right보다 높은 언덕 깎기
		for (int j = N - 1; j >= 0; j--) {
			int n = arr[j];

			if (n > right) {
				int diff = n - right;
				sum += diff * diff;
			}
			else break;
		}

		ans = min(ans, sum);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 낮은 순으로 정렬
	sort(arr, arr + N);

	solve();

	cout << ans;
}