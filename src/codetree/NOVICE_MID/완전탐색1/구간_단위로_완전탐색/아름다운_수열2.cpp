#include <iostream>

using namespace std;

#define MAX_N 100
#define MAX_NUM 100

int N;
int M;
int arr[MAX_N + 1];
int cntB[MAX_NUM + 1];
int cntA[MAX_NUM + 1];

int main() {

	cin >> N >> M;
	// 수열 A 입력 받기
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	// 수열 B 각 원소의 개수 저장
	for (int i = 0; i < M; i++) {
		int n;
		cin >> n;
		cntB[n]++;
	}

	int ans = 0;
	// 수열 A에서 M개를 고름
	for (int i = 0; i <= N - M; i++) {
		fill(cntA, cntA + MAX_NUM + 1, 0);

		for (int j = i; j < i + M; j++) {
			int n = arr[j];
			cntA[n]++;	// 고른 수의 개수 저장
		}

		bool flag = true;
		// A에서 고른 수와 B가 가지고 있는 숫자가 같은지 확인
		for (int k = 1; k <= MAX_NUM; k++) {
			if (cntA[k] != cntB[k]) {
				flag = false;
				break;
			}
		}

		// 같은 경우
		if (flag) ans++;
	}

	cout << ans;
}