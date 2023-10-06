#include <iostream>
#include <string>

using namespace std;

#define R 2
#define N 3
#define MAX_NUM 9

int arr[N][N];
int tr[R];
int ans;

// 이겼는지 체크
bool check(int a, int b) {
	// 가로줄 검사
	for (int i = 0; i < N; i++) {
		int cnt1 = 0;
		int cnt2 = 0;
		for (int j = 0; j < N; j++) {
			if (arr[i][j] == a) cnt1++;
			else if (arr[i][j] == b) cnt2++;
		}

		if (cnt1 + cnt2 == 3 && cnt1 != 3 && cnt2 != 3) return true;
	}

	// 세로줄 검사
	for (int j = 0; j < N; j++) {
		int cnt1 = 0;
		int cnt2 = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i][j] == a) cnt1++;
			else if (arr[i][j] == b) cnt2++;
		}

		if (cnt1 + cnt2 == 3 && cnt1 != 3 && cnt2 != 3) return true;
	}

	// 대각선 검사
	int cnt1 = 0;
	int cnt2 = 0;
	for (int i = 0; i < N; i++) {
		if (arr[i][i] == a) cnt1++;
		else if (arr[i][i] == b) cnt2++;
	}
	if (cnt1 + cnt2 == 3 && cnt1 != 3 && cnt2 != 3) return true;

	cnt1 = 0;
	cnt2 = 0;
	for (int i = 0; i < N; i++) {
		if (arr[i][2 - i] == a) cnt1++;
		else if (arr[i][2 - i] == b) cnt2++;
	}
	if (cnt1 + cnt2 == 3 && cnt1 != 3 && cnt2 != 3) return true;

	return false;
}

// 조합으로 두 사람 고르기
void solve(int k, int start) {
	if (k == R) {
		// 이겼는지 체크
		bool ret = check(tr[0], tr[1]);
		if (ret) ans++;
	}
	else {
		for (int i = start; i <= MAX_NUM; i++) {
			tr[k] = i;
			solve(k + 1, i + 1);
		};
	}
}

int main() {

	// 입력
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		for (int j = 0; j < N; j++) {
			int n = s[j] - '0';
			arr[i][j] = n;
		}
	}

	solve(0, 0);

	cout << ans;
}