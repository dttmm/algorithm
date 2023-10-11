#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int cnt[MAX_N + 1];		// 각 숫자가 몇 개 있는지
int index[MAX_N + 1];	// 각 숫자의 위치 정보

int solve() {
	int rank = 0;	// 몇 번째로 작은 수인지
	for (int i = 1; i <= MAX_N; i++) {
		// 없는 수는 패쓰
		if (cnt[i] == 0) continue;

		// 몇 번째로 작은지 갱신
		rank++;

		// 두 번쨰로 작은 수
		if (rank == 2) {
			// 해당 수가 하나만 있는 경우
			if (cnt[i] == 1) return index[i];
			// 여러개 있는 경우
			else return -1;
		}
	}

	// 두번쨰로 작은 수가 없는 경우
	return -1;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int n;
		cin >> n;

		cnt[n]++;
		index[n] = i;
	}

	int ret = solve();

	cout << ret;
}