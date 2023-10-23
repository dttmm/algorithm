#include <iostream>
#include <deque>
#include <string>

using namespace std;

int N;
deque<int> dq;

int main() {

	cin >> N;

	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		if (s == "push_front") {
			int n;
			cin >> n;

			dq.push_front(n);
		}
		else if (s == "push_back") {
			int n;
			cin >> n;

			dq.push_back(n);
		}
		else if (s == "pop_front") {
			cout << dq.front() << "\n";

			dq.pop_front();
		}
		else if (s == "pop_back") {
			cout << dq.back() << "\n";

			dq.pop_back();
		}
		else if (s == "size") {
			cout << dq.size() << "\n";
		}
		else if (s == "empty") {
			cout << dq.empty() << "\n";
		}
		else if (s == "front") {
			cout << dq.front() << "\n";
		}
		else if (s == "back") {
			cout << dq.back() << "\n";
		}
	}
}