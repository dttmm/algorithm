#include <iostream>
#include <algorithm>
#include <cstdlib>

using namespace std;

#define MAX_A 1000
#define MAX_T 100

int T;
int a;
int b;
int ans;
int sMax;				// S의 총 개수
int nMax;				// N의 총 개수
int sArr[MAX_T + 1];	// S가 있는 위치
int nArr[MAX_T + 1];	// N이 있는 위치
int sR[MAX_A + 1];		// 나랑 오른쪽에서 가장 가까운 S의 위치
int sL[MAX_A + 1];		// 나랑 왼쪽에서 가장 가까운 S의 위치
int sD[MAX_A + 1];		// 나랑 양쪽에서 가장 가까운 S와의 거리
int nR[MAX_A + 1];		// 나랑 오른쪽에서 가장 가까운 N의 위치
int nL[MAX_A + 1];		// 나랑 왼쪽에서 가장 가까운 N의 위치
int nD[MAX_A + 1];		// 나랑 양쪽에서 가장 가까운 N과의 거리

// 나랑 오른쪽에서 가장 가까운 녀석 구하기
void setR(int* arr, int* arrR, int max) {
	fill_n(arrR, MAX_A, MAX_A * 2);
	int prev = -1;
	for (int i = 0; i < max; i++) {
		int n = *(arr + i);

		for (int j = prev + 1; j <= n; j++) {
			*(arrR + j) = n;
		}

		prev = n;
	}
}

// 나랑 왼쪽에서 가장 가까운 녀석 구하기
void setL(int* arr, int* arrL, int max) {
	fill_n(arrL, MAX_A, MAX_A * -2);
	int prev = MAX_A + 1;
	for (int i = 0; i < max; i++) {
		int n = *(arr + max - 1 - i);

		for (int j = prev - 1; j >= n; j--) {
			*(arrL + j) = n;
		}

		prev = n;
	}
}

// 나랑 양쪽에서 가장 가까운 녀석과의 거리 구하기
void setD(int* arrR, int* arrL, int* arrD) {
	for (int i = a; i <= b; i++) {
		int diffR = abs(*(arrR + i) - i);
		int diffL = abs(*(arrL + i) - i);
		*(arrD + i) = min(diffR, diffL);
	}
}

// d1이 d2보다 작거나 같은 경우 구하기
void solve() {
	for (int i = a; i <= b; i++) {
		if (sD[i] <= nD[i]) ans++;
	}
}

int main() {

	// 입력
	cin >> T >> a >> b;

	for (int i = 0; i < T; i++) {
		char c;
		int x;
		cin >> c >> x;

		if (c == 'S') sArr[sMax++] = x;
		else  nArr[nMax++] = x;
	}

	// 정렬
	sort(sArr, sArr + sMax);
	sort(nArr, nArr + nMax);

	// 오른쪽에서 가까운 녀석 구하기
	setR(sArr, sR, sMax);
	setR(nArr, nR, nMax);

	// 왼쪽에서 가까운 녀석 구하기
	setL(sArr, sL, sMax);
	setL(nArr, nL, nMax);

	// 양쪽에서 가까운 녀석과의 거리 구하기
	setD(sR, sL, sD);
	setD(nR, nL, nD);

	// 정답 구하기
	solve();

	cout << ans;
}