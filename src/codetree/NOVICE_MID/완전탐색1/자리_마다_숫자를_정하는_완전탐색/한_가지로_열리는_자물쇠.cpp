#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 100
#define R 3

int N;
int tr[R];
int key[R];	// 자물쇠 번호
int ans;

// 중복 순열
void solve(int k) {
	if (k == R) {
		bool flag = false;
		// 뽑은 숫자와 자물쇠 번호 비교
		for (int i = 0; i < 3; i++) {
			// 숫자 차이가 2 이내인 경우
			if (abs(tr[i] - key[i]) <= 2) {
				flag = true;
				break;
			}
		}

		if (flag) ans++;
	}
	else {
		for (int i = 1; i <= N; i++) {
			int n = i;
			tr[k] = n;
			solve(k + 1);
		}
	}
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> key[i];
	}

	// 중복 순열
	solve(0);

	cout << ans;
}