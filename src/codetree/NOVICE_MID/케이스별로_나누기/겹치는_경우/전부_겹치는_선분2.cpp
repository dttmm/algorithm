#include <iostream>

using namespace std;

#define MAX_N 100

int N;
int visited[MAX_N + 1];	// 해당 칸에 몇 개 선분이 겹쳐있는지
int x1Arr[MAX_N];
int x2Arr[MAX_N];

// 선분 그리기
void draw(int x1, int x2) {
	for (int i = x1; i <= x2; i++) {
		visited[i]++;
	}
}

// 1개를 제거했을 때 모두 겹치는지 체크
bool check(int x1, int x2) {
	for (int i = 1; i <= MAX_N; i++) {
		// 제거할 선분이 있는 영역
		// -> 제거할 선분을 포함하여 N개가 겹쳐있어야, 해당 선분을 제거하였을 때 N-1개가 겹쳐있게 됨
		if (i >= x1 && i <= x2) {
			if (visited[i] == N) return true;
		}
		// 제거할 선분이 없는 영역
		// -> 해당 선분을 제거하든 말든 N-1개가 겹쳐있어야 됨
		else {
			if (visited[i] == N - 1) return true;
		}
	}

	return false;
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		int x1, x2;
		cin >> x1 >> x2;

		// 선분 그리기
		draw(x1, x2);
		x1Arr[i] = x1;
		x2Arr[i] = x2;
	}

	// 선분 하나씩 제거해보면서 검사
	for (int i = 0; i < N; i++) {
		bool ret = check(x1Arr[i], x2Arr[i]);

		if (ret) {
			cout << "Yes";
			return 0;
		}
	}

	cout << "No";
}