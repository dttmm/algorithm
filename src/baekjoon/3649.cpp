#include <iostream>
#include <fstream>
#include <algorithm>

/*
* 설계 4분 구현 5분 삽질 13분
* 투포인터
* 레고 조각을 정렬한 다음
* 양쪽에서 투포인터를 이용하여
* 구멍의 길이와 일치하는 두 레고 조각을 찾음
* 
* 두 레고 조각 차이의 절대값이 커야하니까
* 양쪽에서 부터 시작함
* 
* 출력초과
* ??
* 뭐가 문제인지 한참 찾다가
* arr 배열 초기화 한답시고 arr[10000001] = {} 해줬는데
* 이헐게 초기화 못하네
* 이거 없애니까 정답
* 이미 선언된 배열 초기화 하려면 fill_n()써야 되네
*/

using namespace std;

int X;
int N;
int arr[1000001];

// 투포인터
void solve() {
	int L = 0;
	int R = N - 1;
	while (L < R) {
		int sum = arr[L] + arr[R];

		// 구멍을 막을 수 있는 경우
		if (sum == X) {
			cout << "yes " << arr[L] << " " << arr[R] << endl;;
			return;
		}
		// 구멍보다 더 큰 경우
		else if (sum > X) {
			R--;
		}
		//구멍보다 더 작은 경우
		else {
			L++;
		}
	}

	cout << "danger" << endl;
}

int main() {

	freopen("res/baekjoon/3649.txt", "r", stdin);

	// 입력 없을 때까지 반복
	while (cin >> X) {
		X *= 10000000;
		cin >> N;

		// 입력 받기
		for (int i = 0; i < N; i++) {
			cin >> arr[i];
		}

		// 정렬
		sort(arr, arr + N);

		// 투포인터
		solve();
	}
}