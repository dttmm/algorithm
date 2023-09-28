#include <iostream>
#include <cmath>

using namespace std;

#define MAX_N 100
#define MAX_X 100

int N;
int K;
int arr[MAX_X + 1];	// x좌표에 사탕이 몇 개 있는지

int main() {

	// 입력 받기
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int cnt;
		int x;
		cin >> cnt >> x;

		arr[x] += cnt;
	}

	// c-k ~ c+k 구간은 2k+1만큼의 구간과 똑같음
	int kk = 2 * K + 1;
	// 2k+1가 최대 좌표 범위인 101을 넘어갈 수 있으므로 최대 kk값 조정
	kk = min(kk, MAX_X + 1);

	int sum = 0;
	int ans = 0;
	// 좌표 순회하면서 kk구간 만큼의 사탕 개수 구함
	for (int i = 0; i < MAX_X + 1; i++) {
		if (i < kk) {
			sum += arr[i];
			ans = max(ans, sum);
			continue;
		}

		sum += arr[i];
		sum -= arr[i - kk];
		ans = max(ans, sum);
	}

	cout << ans;
}