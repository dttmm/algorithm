#include <fstream>
#include <iostream>
#include <algorithm>

/*
* 설계 2분 구현 4분
* 그리디
* 상자의 크기를 배열에 저장한다음
* 배열을 큰 순서대로 정렬
* 상자를 하나씩 꺼내보면서 사탕을 담고
* 다 담을 때까지 상자를 사용
* 정렬할 때 큰 숫자가 먼저 오게 하도록
* 배열에 숫자 넣고 뺄 때, -1곱해줌
*/

using namespace std;

#define MAX_N 1000

int T;
int N;
int J;
int arr[MAX_N];	// 상자의 크기 저장

// 상자에 사탕 담기
int solve() {
	int remain = J;	// 남아 있는 상탕의 수
	int cnt = 0;	// 사용한 상자 수
	int index = 0;

	// 남은 사탕이 없을 때까지 반복
	while (remain > 0) {
		int n = (arr[index++]) * -1;
		remain -= n;
		cnt++;
	}

	return cnt;
}

int main() {

	freopen("res/baekjoon/11256.txt", "r", stdin);

	// 테케
	cin >> T;
	for (int tc = 0; tc < T; tc++) {
		// 입력 받기
		cin >> J >> N;
		for (int i = 0; i < N; i++) {
			int r;
			int c;
			cin >> r >> c;

			arr[i] = (r * c) * -1;
		}

		// 정렬
		sort(arr, arr + N);

		// 결과
		int ret = solve();

		cout << ret << "\n";
	}
}