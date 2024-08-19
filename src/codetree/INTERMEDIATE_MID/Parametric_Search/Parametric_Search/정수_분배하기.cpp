#include<iostream>

using namespace std;

#define MAX_N 10000
#define MAX_M 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int M;
int arr[MAX_N];

// k를 m개 이상 만들수 있는지 여부 반환
bool isP(int k) {
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		cnt += arr[i] / k;
	}

	return cnt >= M;
}

int solve() {
	int s = 1;
	int e = MAX_M;

	int maxK = 0;
	while (s <= e) {
		int mid = (s + e) / 2;

		if (isP(mid)) {
			s = mid + 1;
			maxK = MAX(maxK, mid);
		}
		else {
			e = mid - 1;
		}
	}

	return maxK;
}

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int ret = solve();

	cout << ret;
}