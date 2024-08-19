#include <iostream>
#include<unordered_map>

using namespace std;

#define MAX_N 100000

int N;
int arr[MAX_N + 2];
long long L[MAX_N + 2];	// 1~i까지 누적합
long long R[MAX_N + 2];	// N~i까지 누적합
long long diff[MAX_N + 2]; // L[i] - R[i+1]
unordered_map<long long, int> RMAP;	// 기준에서 오른쪽에 있는 원소들 모음
unordered_map<long long, int> LMAP;	// 기준에서 왼쪽에 있는 원소들 모음

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}

	// 1~i까지 누적합 구하기
	for (int i = 1; i <= N; i++) {
		L[i] = L[i - 1] + arr[i];
	}

	// N~i까지 누적합 구하기
	for (int i = N; i >= 1; i--) {
		R[i] = R[i + 1] + arr[i];
	}

	// L[i] - R[i+1] 구하기
	for (int i = 0; i < N; i++) {
		diff[i] = L[i] - R[i + 1];
	}

	// 기준을 1부터 잡아서 시작할거라
	// 1보다 오른쪽에 있는 원소들은 RAMP에 넣고
	for (int i = 2; i < N; i++) {
		int n = diff[i];
		RMAP[n]++;
	}
	// 1에 해당하는 LMAP에 넣음
	LMAP[diff[1]]++;

	// 기준 i를 하나씩 증가시키며
	long long cnt = 0;
	for (int i = 2; i < N - 1; i++) {
		int n = diff[i];

		// 기준을 중신으로 오른쪽 왼쪽에 해당하는 원소 업데이트
		RMAP[n]--;

		// 2개의 구간으로 나눌 수 있는 지점 찾았을 때
		// 2개의 구간을 또 2개의 구간으로 나누어야 하기 때문에 2의 배수인지 확인
		if (n == 0 && L[i] % 2 == 0) {
			int target = L[i];

			// 2개의 구간이 또 2개의 구간으로 나누어지는 경우 계산
			cnt += LMAP[-1 * target] * RMAP[target];
		}

		LMAP[n]++;
	}

	cout << cnt;
}