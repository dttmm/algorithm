#include <iostream>
#include <queue>
#include <string>

using namespace std;

int N;
queue<int> q;

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		if (s == "push") {
			int n;
			cin >> n;

			q.push(n);
		}
		else if (s == "pop") {
			cout << q.front() << "\n";

			q.pop();
		}
		else if (s == "size") {
			cout << q.size() << "\n";
		}
		else if (s == "empty") {
			cout << q.empty() << "\n";
		}
		else if (s == "front") {
			cout << q.front() << "\n";
		}
	}
}