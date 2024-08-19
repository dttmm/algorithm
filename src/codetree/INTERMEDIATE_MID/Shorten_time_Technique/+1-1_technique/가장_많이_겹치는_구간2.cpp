#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
pair<int, int> arr[MAX_N * 2];

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		arr[2 * i] = make_pair(a, 1);	// 시작지점
		arr[2 * i + 1] = make_pair(b, -1);	// 종료지점
	}

	// 정렬
	sort(arr, arr + 2 * N);

	int maxVal = 0;
	int cnt = 0;	// 겹쳐있는 선분의 개수
	for (int i = 0; i < 2 * N; i++) {
		auto it = arr[i];

		cnt += it.second;
		maxVal = MAX(maxVal, cnt);
	}

	cout << maxVal;
}