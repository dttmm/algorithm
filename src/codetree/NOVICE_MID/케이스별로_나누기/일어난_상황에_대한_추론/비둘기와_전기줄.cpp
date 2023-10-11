#include <iostream>

using namespace std;

#define MAX_X 10

int N;
int arr[MAX_X + 1]; // i번 비둘기의 현재 방향

// 방향이 바뀌었는지 체크
// true: 방향 바뀜
bool solve(int num, int dir) {
	// 아직 방향이 없는 경우
	if (arr[num] == -1) {
		arr[num] = dir;
		return false;;
	}

	// 이전 방향과 똑같은 방향인 경우
	if (arr[num] == dir) return false;

	// 방향이 바뀐 경우
	arr[num] = dir;
	return true;
}

int main() {

	// 모든 비둘기 방향 없다고 초기화
	fill_n(arr + 1, MAX_X, -1);

	// 입력
	cin >> N;
	int cnt = 0;
	for (int i = 0; i < N; i++) {
		int num;
		int dir;
		cin >> num >> dir;

		// 방향 바뀌었는지 체크
		bool ret = solve(num, dir);
		if (ret) cnt++;
	}

	cout << cnt;
}