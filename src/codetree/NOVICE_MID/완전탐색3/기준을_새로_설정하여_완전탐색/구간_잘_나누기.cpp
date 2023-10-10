#include <iostream>
#include <algorithm>
#include <climits>

using namespace std;

#define MAX_N 100
#define MAX_X 100

int N;
int M;
int arr[MAX_N];

bool solve(int x) {
	int sum = 0;	// 구간 합
	int cnt = 0;	// 칸막이 횟수

	for (int i = 0; i < N; i++) {
		int n = arr[i];

		// 현재 숫자가 최소값보다 크면 불가능
		if (n > x) return false;

		// 현재 숫자를 현재 구간의 합에 더함
		sum += n;

		// 구간의 합이 최소값보다 크면
		if (sum > x) {
			cnt++;		// 칸막이를 치고
			sum = n;	// 새로운 구간 시작
		}

		// 칸막이 개수가 M-1보다 크면 안됨
		if (cnt > M - 1) return false;
	}

	return true;
}

int main() {

	// 입력
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	int limit = MAX_X * MAX_X;
	// i: 최대값이 최소가 되는 수
	// 정답이 되는 i를 기준으로 삼고
	// i가 정답일 때, 정답이 되는 조건을 만족하는지 검사
	for (int i = 1; i <= limit; i++) {
		bool ret = solve(i);

		if (ret) {
			cout << i;
			break;
		}
	}
}