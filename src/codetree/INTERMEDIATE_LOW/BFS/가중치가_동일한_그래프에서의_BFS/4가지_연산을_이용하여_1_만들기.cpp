#include <iostream>
#include <queue>

using namespace std;

#define MAX_N 1000000

int N;
int visited[MAX_N + 1];

int solve() {
	queue<int> q;
	q.push(N);
	visited[N] = 1;

	while (!q.empty()) {
		int n = q.front();
		q.pop();

		if (n == 1) return visited[1] - 1;

		// 현재 숫자에서 1 뺄 수 있는 경우
		if (visited[n - 1] == 0) {
			q.push(n - 1);
			visited[n - 1] = visited[n] + 1;
		}

		// 현재 숫자에서 1 더할 수 있는 경우
		if (visited[n + 1] == 0) {
			q.push(n + 1);
			visited[n + 1] = visited[n] + 1;
		}

		// 현재 숫자에서 2 나눌 수 있는 경우
		if (n % 2 == 0 && visited[n / 2] == 0) {
			q.push(n / 2);
			visited[n / 2] = visited[n] + 1;
		}

		// 현재 숫자에서 3 나눌 수 있는 경우
		if (n % 3 == 0 && visited[n / 3] == 0) {
			q.push(n / 3);
			visited[n / 3] = visited[n] + 1;
		}
	}
}

int main() {

	// 입력
	cin >> N;

	int ret = solve();

	cout << ret;
}