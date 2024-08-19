#include <iostream>

using namespace std;

/*
* in 1920 out 10507
*/

#define MAX_N 200000
#define MAX_X 1000000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int P;
bool arr[MAX_X + 2];	// i일에 공부 했는지 여부
int L[MAX_X + 2];	// 0~i일까지 공부한 날의 개수
// 주어지는 공부 날은 0 ~ 10^6이라서
// 0을 1일로, 10^6을 10^6+1일로 저장함

// 초기화
void init() {
	fill_n(arr, MAX_X + 1, false);
}

// L배열 세팅
void setL() {
	for (int i = 1; i <= MAX_X + 1; i++) {
		L[i] = L[i - 1] + arr[i];
	}
}

// n일 이상 연속 공부가 가능한지 체크
bool isPossible(int n) {
	for (int i = n; i <= MAX_X + 1; i++) {
		// i-n일 ~ i일 까지 총 공부한 날에 p일을 더했을 때
		// n일 이상공부가 가능한 경우
		if (L[i] - L[i - n] + P >= n) return true;
	}
	return false;
}

// 이분 탐색
int solve() {
	int maxN = 1; // 최대로 가능한 연속 공부 일수
	int s = 1;
	int e = MAX_X + 1;

	while (s <= e) {
		int mid = s + (e - s) / 2;

		// mid일 이상 연속 공부가 가능한 경우
		if (isPossible(mid)) {
			s = mid + 1;
			maxN = MAX(maxN, mid);
		}
		else {
			e = mid - 1;
		}
	}

	return maxN;
}

int main() {

	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	int T;
	cin >> T;
	for (int tc = 1; tc <= T; tc++) {
		cin >> N >> P;

		init();

		for (int i = 1; i <= N; i++) {
			int n;
			cin >> n;
			arr[n + 1] = true;
		}

		setL();

		int ret = solve();

		cout << "#" << tc << " " << ret << "\n";
	}
}