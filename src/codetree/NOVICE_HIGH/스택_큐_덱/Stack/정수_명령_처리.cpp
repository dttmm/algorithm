#include <iostream>
#include <stack>
#include <string>

using namespace std;

int N;
stack<int> s;

int main() {

	cin >> N;
	for (int i = 0; i < N; i++) {
		string command;

		cin >> command;

		if (command == "push") {
			int n;
			cin >> n;

			s.push(n);
		}
		else if (command == "pop") {
			cout << s.top() << "\n";
			s.pop();
		}
		else if (command == "size") {
			cout << s.size() << "\n";
		}
		else if (command == "empty") {
			cout << s.empty() << "\n";
		}
		else if (command == "top") {
			cout << s.top() << "\n";
		}
	}
}