#include <iostream>
#include <deque>

using namespace std;

int N;
deque<int> dq;

int solve() {
	while (dq.size() != 1) {
		dq.pop_front();

		int n = dq.front();
		dq.pop_front();

		dq.push_back(n);
	}

	return dq.front();
}

int main() {

	cin >> N;

	for (int i = 1; i <= N; i++) {
		dq.push_back(i);
	}

	int ret = solve();

	cout << ret;
}