#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_X 100
#define MAX_N 10
#define R 3

int N;
int cntArr[MAX_X + 1];	// 선분이 차지하고 있는 점 카운트
int startArr[MAX_N];	// i번 선분의 시작점
int endArr[MAX_N];		// j번 선분의 시작점
int tr[R];
int ans;

// 겹친 선분이 있는지 검사
bool isNotOver() {
	for (int i = 0; i <= MAX_X; i++) {
		if (cntArr[i] > 1) return false;
	}
	return true;
}

// 조합
void solve(int k, int s) {
	if (k == R) {
		// 뽑은 선분 카운트 제거
		for (int i = 0; i < R; i++) {
			int index = tr[i];
			int start = startArr[index];
			int end = endArr[index];

			for (int x = start; x <= end; x++) {
				cntArr[x]--;
			}
		}

		// 겹친 선분이 있는지 검사
		bool ret = isNotOver();

		// 제거한 카운트 복구
		for (int i = 0; i < R; i++) {
			int index = tr[i];
			int start = startArr[index];
			int end = endArr[index];

			for (int x = start; x <= end; x++) {
				cntArr[x]++;
			}
		}

		// 겹친 선분이 없는 경우
		if (ret) ans++;
	}
	else {
		for (int i = s; i < N; i++) {
			tr[k] = i;
			solve(k + 1, i + 1);
		}
	}
}

int main() {

	// 입력 받기
	cin >> N;
	for (int i = 0; i < N; i++) {
		int start;
		int end;
		cin >> start >> end;

		for (int x = start; x <= end; x++) {
			cntArr[x]++;
		}
		startArr[i] = start;
		endArr[i] = end;
	}

	solve(0, 0);

	cout << ans;
}