#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define MAX_X 100000

int N;
int arr[MAX_N];
bool d[MAX_X + 1];
int sum;

// dp
bool solve() {
	// 총합이 홀수면 동일하게 분할 불가
	if (sum & 1)return false;
	// 총합의 반 구함
	int half = sum / 2;

	d[0] = true;
	// 숫자 하나 골라서
	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// 숫자들의 합이 x를 만족하는지 검사
		for (int x = half; x >= n; x--) {
			if (d[x - n]) {
				d[x] = true;

				// 선택된 숫자들이 합이 half라면 -> 나머지 숫자들의 합도 half니까
				// 균등하게 분할 됨!
				if (x == half) return true;
			}
		}
	}
	return false;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
		sum += arr[i];
	}

	bool ret = solve();

	if (ret) cout << "Yes";
	else cout << "No";
}