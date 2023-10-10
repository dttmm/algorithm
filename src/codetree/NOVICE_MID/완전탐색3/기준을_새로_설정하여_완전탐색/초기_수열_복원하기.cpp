#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_N 1000

int N;
int arr[MAX_N];	// 두 수의 합 정보
int tr[MAX_N];	// 정답 저장하기 위한 배열
bool visited[MAX_N + 1];	// 숫자 사용했는지 여부

bool solve(int k, int n) {
	if (k == N) return true;
	else {
		int target = arr[k - 1] - n;	// 두 수의 합 정보를 바탕으로 가능한 다음 숫자 결정

		// 나올 수 없는 수인 경우
		if (target <= 0) return false;
		// 이미 해당 수를 사용한 경우	<- 1부터 N까지 숫자들을 한번만 사용할 수 있기 떄문
		if (visited[target]) return false;

		visited[target] = true;
		tr[k] = target;

		return solve(k + 1, target);
	}
}

int main() {

	// 입력
	cin >> N;
	for (int i = 0; i < N - 1; i++) {
		cin >> arr[i];
	}

	// n: 시작 숫자 결정
	// 시작 숫자 결정 후, 입력으로 받은 두 수의 합 정보를 바탕으로
	// 다음 숫자를 결정함. 총 N개의 숫자를 모두 결정할 수 있다면 그것이 바로 정답
	for (int n = 1; n <= N; n++) {
		fill(visited, visited + MAX_N + 1, false);	// 숫자 사용여부 초기화
		visited[n] = true;
		tr[0] = n;
		bool ret = solve(1, n);
		if (ret) break;
	}

	for (int i = 0; i < N; i++) {
		cout << tr[i] << " ";
	}
}