#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int x1Arr[MAX_N];
int x2Arr[MAX_N];
bool overArr[MAX_N];

// 겹치는지 반펼하기
void solve() {
	// i: 기준
	for (int i = 0; i < N; i++) {
		// 전체 순회하면서 i와 겹치는지 판별
		for (int j = 0; j < N; j++) {
			if (i == j) continue;

			// x2가 나보다 크고 x1이 나보다 작으면 겹침
			if (x2Arr[j] > x2Arr[i] && x1Arr[j] < x1Arr[i]) {
				overArr[i] = true;
				overArr[j] = true;
			}
		}
	}
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x1;
		int x2;
		cin >> x1 >> x2;

		x1Arr[i] = x1;
		x2Arr[i] = x2;
	}

	// 겹치는지 반펼하기
	solve();

	// 겹치지 않는 개수 구하기
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		if (!overArr[i]) cnt++;
	}

	cout << cnt;
}