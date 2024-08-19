#include <iostream>

using namespace std;

#define MAX_N 100000

struct Node {
	int x;
	int y;

	Node() {}
	Node(int x_, int y_) {
		x = x_;
		y = y_;
	}

	int operator-(Node o) {
		int n1 = x - o.x;
		int n2 = y - o.y;
		n1 = n1 < 0 ? -1 * n1 : n1;
		n2 = n2 < 0 ? -1 * n2 : n2;
		return n1 + n2;
	}
}arr[MAX_N];

int N;
int diff[MAX_N];
int R[MAX_N];
int L[MAX_N];

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;

		arr[i] = Node(x, y);
	}

	// i-1과 i와의 차이 계산
	for (int i = 1; i < N; i++) {
		diff[i] = arr[i] - arr[i - 1];
	}

	// 0~i까지 누적 차이 계산
	for (int i = 1; i < N; i++) {
		L[i] = L[i - 1] + diff[i];
	}

	// i~N-1까지 누적 차이 계산
	for (int i = N - 2; i >= 0; i--) {
		R[i] = R[i + 1] + diff[i + 1];
	}

	int min = 1000000000;
	for (int i = 1; i < N - 1; i++) {
		int ret = L[i - 1] + R[i + 1] + (arr[i + 1] - arr[i - 1]);
		min = ret < min ? ret : min;
	}

	cout << min;
}