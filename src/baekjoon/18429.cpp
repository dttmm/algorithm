#include <iostream>
#include <fstream>

/*
* 설계 2분 구현 5분
* 순열
* 순열을 통해 각 날마다 운동을 하나씩 골라가면
* 이제까지 증가한 중량이 감소한 중량보다 낮을 경우 가지치기를 함
*/

using namespace std;

#define MAX_N 8

int N;
int K;
int arr[MAX_N];
bool visited[MAX_N];
int ans;

// 순열
void solve(int k, int sum) {
	// 이제까지 증가한 중량이 감소한 중량보다 낮을 경우
	if (sum < k * K) return;
	// N일 까지 운동한 경우
	if (k == N) {
		ans++;
	}
	else {
		for (int i = 0; i < N; i++) {
			if (visited[i])  continue;

			visited[i] = true;
			int n = arr[i];
			solve(k + 1, sum + n);
			visited[i] = false;
		}
	}
}

int main() {

	freopen("res/baekjoon/18429.txt", "r", stdin);

	// 입력 받기
	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}

	// 순열
	solve(0, 0);

	cout << ans;
}