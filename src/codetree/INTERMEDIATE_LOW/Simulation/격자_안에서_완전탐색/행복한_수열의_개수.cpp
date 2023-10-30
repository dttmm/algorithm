#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100

int N;
int M;
int arr[MAX_N][MAX_N];
bool row[MAX_N];	// 각 행에 행복한 수가 몇 개인지
bool col[MAX_N];	// 각 열에 행복한 수가 몇 개인지

// 각 행의 행복한 수 카운트
void setRow() {
	for (int i = 0; i < N; i++) {
		int prev = arr[i][0];
		int len = 1;	// 동일한 수열의 길이
		bool flag = false;	// 행복한 수열이진 플래그
		if (len == M) flag = true;

		for (int j = 1; j < N; j++) {
			int n = arr[i][j];

			// 이전 수와 같은 경우
			if (n == prev) len++;
			else len = 1;

			// 행복한 수 조건을 만족하는 경우
			if (len == M)flag = true;

			prev = n;
		}

		row[i] = flag;
	}
}

// 각 열의 행복한 수 카운트
void setCol() {
	for (int j = 0; j < N; j++) {
		int prev = arr[0][j];
		int len = 1;
		bool flag = false;
		if (len == M) flag = true;

		for (int i = 1; i < N; i++) {
			int n = arr[i][j];

			if (n == prev) len++;
			else len = 1;

			if (len == M)flag = true;

			prev = n;
		}

		col[j] = flag;
	}
}

// 총 개수 세줌
int solve() {
	int total = 0;

	for (int i = 0; i < N; i++) {
		total += row[i];
		total += col[i];
	}

	return total;
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}

	setRow();

	setCol();

	int ret = solve();

	cout << ret;
}