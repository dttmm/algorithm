#include <iostream>
#include <algorithm>

using namespace std;

#define MAX_K 10
#define MAX_N 20
#define R 2

int N;
int K;
int arr[MAX_K + 1][MAX_N + 1];	// i경기에서 j번째 사람의 등수
int tr[R];
bool visited[MAX_N + 1];
int ans;

// 순열
void solve(int k) {
	if (k == R) {
		// n1이 n2보다 항상 순위가 높다면 경우의 수++
		int n1 = tr[0];
		int n2 = tr[1];

		// 모든 경기를 돌면서
		for (int k = 1; k <= K; k++) {
			// n1이 n2보다 한번이라도 순위가 낮으면(rank가 높으면) 패쓰
			if (arr[k][n1] > arr[k][n2]) return;
		}

		ans++;
	}
	else {
		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			tr[k] = i;
			solve(k + 1);
			visited[i] = false;
		}
	}
}

int main() {

	// 입력 받기
	cin >> K >> N;
	for (int k = 1; k <= K; k++) {
		for (int rank = 1; rank <= N; rank++) {
			int num;
			cin >> num;

			arr[k][num] = rank;
		}
	}

	// 순열
	solve(0);

	cout << ans;
}