#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int arr[4][4];	// 첫번째 개발자가 i를 냈을 때, 두번째 개발자가 j를 낸 횟수

int main() {

	cin >> N;
	for (int x = 0; x < N; x++) {
		int i, j;
		cin >> i >> j;
		arr[i][j]++;
	}

	// 1>2>3>1 일때
	int case1 = arr[1][2] + arr[2][3] + arr[3][1];

	// 1>3>2>1 일때
	int case2 = arr[1][3] + arr[3][2] + arr[2][1];

	// 두 경우의 최대값
	int ans = max(case1, case2);

	cout << ans;
}