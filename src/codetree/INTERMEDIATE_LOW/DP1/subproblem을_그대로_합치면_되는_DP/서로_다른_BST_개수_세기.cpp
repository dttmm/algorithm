#include <iostream>

using namespace std;

#define MAX_N 19

int N;
int d[MAX_N + 1];

// dp
void solve() {
	d[0] = 1;

	// 노드의 개수가 i일때
	for (int i = 1; i <= N; i++) {
		int sum = 0;

		// 1부터 i까지 루트노드에 위치시켰을 때
		// 양쪽에 둘 수 있는 서브 트리 개수 구해줌
		for (int root = 1; root <= i; root++) {
			// d[root - 1]: 가능한 왼쪽 서브트리
			// d[i - root]: 가능한 오른쪽 서브트리
			sum += d[root - 1] * d[i - root];
		}

		d[i] = sum;
	}
}

int main() {

	// 입력
	cin >> N;

	solve();

	cout << d[N];
}