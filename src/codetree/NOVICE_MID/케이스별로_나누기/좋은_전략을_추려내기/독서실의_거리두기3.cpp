#include <iostream>
#include <algorithm>
#include <string>

using namespace std;

#define MAX_N 1000

int N;
bool arr[MAX_N];

// 가장 가까운 두사람의 거리 구하기
int solve() {
	int minVal = MAX_N;
	for (int i = 0; i < N; i++) {
		if (!arr[i]) continue;
		for (int j = i + 1; j < N; j++) {
			if (!arr[j]) continue;

			minVal = min(minVal, j - i);
			break;
		}
	}

	return minVal;
}

int main() {

	string s;
	// 입력
	cin >> N >> s;
	for (int i = 0; i < N; i++) {
		if (s[i] == '1') arr[i] = true;
	}

	// 가장 거리가 먼 두사람 고르기
	int maxVal = 0;
	int maxI, maxJ;
	for (int i = 0; i < N; i++) {
		if (!arr[i]) continue;
		for (int j = i + 1; j < N; j++) {
			if (!arr[j]) continue;

			if (j - i > maxVal) {
				maxVal = j - i;
				maxI = i;
				maxJ = j;
			}
			break;
		}
	}

	// 두 사람의 중간에 새로운 사람 넣기
	int newIndex = (maxI + maxJ) / 2;
	arr[newIndex] = true;

	int ret = solve();

	cout << ret;
}