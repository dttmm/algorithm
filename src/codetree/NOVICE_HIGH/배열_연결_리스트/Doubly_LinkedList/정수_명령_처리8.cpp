#include <iostream>
#include <list>
#include <string>

using namespace std;

int N;

int main() {

	list<int> list;

	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		cin >> s;

		if (s == "push_front") {
			int n;
			cin >> n;

			list.push_front(n);
		}
		else if (s == "push_back") {
			int n;
			cin >> n;

			list.push_back(n);
		}
		else if (s == "pop_front") {
			cout << list.front() << "\n";
			list.pop_front();
		}
		else if (s == "pop_back") {
			cout << list.back() << "\n";
			list.pop_back();
		}
		else if (s == "size") {
			cout << list.size() << "\n";
		}
		else if (s == "empty") {
			cout << list.empty() << "\n";
		}
		else if (s == "front") {
			cout << list.front() << "\n";
		}
		else if (s == "back") {
			cout << list.back() << "\n";
		}
	}
}