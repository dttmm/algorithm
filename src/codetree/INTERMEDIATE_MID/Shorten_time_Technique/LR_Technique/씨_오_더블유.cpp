#include <iostream>
#include <string>

using namespace std;

#define MAX_N 100000

int N;
char arr[MAX_N + 2];
int CL[MAX_N + 2];	// 왼쪽에서부터 C 누적 개수 셈
int WR[MAX_N + 2];	// 오른쪽에서부터 W 누적 개수 셈


int main() {

	cin >> N;

	string s;
	cin >> s;
	for (int i = 1; i <= N; i++) {
		arr[i] = s[i - 1];
	}

	// CL 세팅
	for (int i = 1; i <= N; i++) {
		CL[i] = CL[i - 1] + (arr[i] == 'C');
	}

	// WR 세팅
	for (int i = N; i >= 1; i--) {
		WR[i] = WR[i + 1] + (arr[i] == 'W');
	}

	// O의 왼쪽 C개수와 오른쪽 R개수 곱하기
	long long total = 0;
	for (int i = 2; i < N; i++) {
		if (arr[i] != 'O') continue;

		total += (CL[i - 1] * WR[i + 1]);
	}

	cout << total;
}