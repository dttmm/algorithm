#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int xrr[MAX_N];
int yrr[MAX_N];

int main() {

	int minXIndex = 0;		// 가장 작은 x를 가지는 인덱스
	int maxYIndex = 0;	// 가장 큰 y를 가지는 인덱스

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> xrr[i] >> yrr[i];

		if (xrr[i] < xrr[minXIndex]) minXIndex = i;
		if (yrr[i] > yrr[maxYIndex]) maxYIndex = i;
	}

	// 가장 작은 x를 가지는 선분을 제외하고 나머지 선분을 포함하는 선분의 길이 구하기
	int minX = 100;
	int maxY = 0;
	for (int i = 0; i < N; i++) {
		if (i == minXIndex) continue;

		minX = min(minX, xrr[i]);
		maxY = max(maxY, yrr[i]);
	}

	int len1 = maxY - minX;

	// 가장 큰 y를 가지는 선분을 제외하고 나머지 선분을 포함하는 선분의 길이 구하기
	minX = 100;
	maxY = 0;
	for (int i = 0; i < N; i++) {
		if (i == maxYIndex) continue;

		minX = min(minX, xrr[i]);
		maxY = max(maxY, yrr[i]);
	}

	int len2 = maxY - minX;

	// 둘 중 최소가 정답
	int ans = min(len1, len2);

	cout << ans;
}