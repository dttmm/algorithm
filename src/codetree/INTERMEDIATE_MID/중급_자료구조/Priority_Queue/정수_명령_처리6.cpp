#include<iostream>
#include<queue>
#include <string>

using namespace std;

int N;
priority_queue<int> pq;

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		if (s == "push") {
			int n;
			cin >> n;

			pq.push(n);
		}
		else if (s == "pop") {
			int n = pq.top();
			pq.pop();

			cout << n << "\n";
		}
		else if (s == "size") {
			cout << pq.size() << "\n";
		}
		else if (s == "empty") {
			cout << pq.empty() << "\n";
		}
		else if (s == "top") {
			cout << pq.top() << "\n";
		}
	}
}