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

		if (n == 0) {
			if (pq.empty()) {
				cout << "0\n";
				continue;
			}
			cout << pq.top() << "\n";
			pq.pop();
		}
		else {
			pq.push(n);
		}
	}
}