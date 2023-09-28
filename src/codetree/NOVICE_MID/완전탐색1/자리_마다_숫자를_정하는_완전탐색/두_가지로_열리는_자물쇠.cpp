#include <iostream>
#include<algorithm>
using namespace std;

#define R 3
#define MAX_N 100

int N;
int key1[R];	// 첫번째 자물쇠
int key2[R];	// 두번째 자물쇠
int tr[R];
int ans;

// 중복 순열
void solve(int k) {
	if (k == R) {
		// 자물쇠를 열 수 있는지 플래그
		bool flag = true;

		// 첫번째 자물쇠와 비교
		for (int i = 0; i < R; i++) {
			int diff = abs(tr[i] - key1[i]);
			// 2 이내에 근접해 있지 않은 경우
			if (diff > 2 && diff < N - 2) {	// 두 숫자의 차이가 N-2보다 크다면 원형으로 2 이내에 인접해있음
				flag = false;
				break;
			}
		}

		// 첫번째 자물쇠가 열리는 경우
		if (flag) {
			ans++;
			return;
		}

		flag = true;
		// 두번째 자물쇠와 비교
		for (int i = 0; i < R; i++) {
			int diff = abs(tr[i] - key2[i]);
			if (diff > 2 && diff < N - 2) {
				flag = false;
				break;
			}
		}

		// 두번째 자물쇠가 열리는 경우
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
	for (int i = 0; i < R; i++) {
		cin >> key1[i];
	}
	for (int i = 0; i < R; i++) {
		cin >> key2[i];
	}

	// 중복 순열
	solve(0);

	cout << ans;
}