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

		// ���� ���ڿ��� 1 �� �� �ִ� ���
		if (visited[n - 1] == 0) {
			q.push(n - 1);
			visited[n - 1] = visited[n] + 1;
		}

		// ���� ���ڿ��� 1 ���� �� �ִ� ���
		if (visited[n + 1] == 0) {
			q.push(n + 1);
			visited[n + 1] = visited[n] + 1;
		}

		// ���� ���ڿ��� 2 ���� �� �ִ� ���
		if (n % 2 == 0 && visited[n / 2] == 0) {
			q.push(n / 2);
			visited[n / 2] = visited[n] + 1;
		}

		// ���� ���ڿ��� 3 ���� �� �ִ� ���
		if (n % 3 == 0 && visited[n / 3] == 0) {
			q.push(n / 3);
			visited[n / 3] = visited[n] + 1;
		}
	}
}

int main() {

	// �Է�
	cin >> N;

	int ret = solve();

	cout << ret;
}