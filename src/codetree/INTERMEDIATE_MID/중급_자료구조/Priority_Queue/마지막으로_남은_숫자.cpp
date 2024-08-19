#include<iostream>
#include<queue>

using namespace std;

int N;
priority_queue<int> pq;

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		int n;
		cin >> n;

		pq.push(n);
	}

	while (pq.size() >= 2) {
		int n1 = pq.top();
		pq.pop();

		int n2 = pq.top();
		pq.pop();

		int diff = n1 - n2;

		if (diff == 0) continue;

		pq.push(diff);
	}

	if (pq.empty()) {
		cout << -1;
	}
	else {
		cout << pq.top();
	}
}