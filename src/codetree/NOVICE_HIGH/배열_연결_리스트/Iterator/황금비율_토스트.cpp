#include <iostream>
#include <list>
#include <string>

using namespace std;

int N;
int M;
list<char> li;
list<char>::iterator it;

int main() {

	string s;
	cin >> N >> M;
	cin >> s;
	for (int i = 0; i < N; i++) {
		char c = s[i];

		li.push_back(c);
	}

	it = li.end();

	for (int i = 0; i < M; i++) {
		char c;
		cin >> c;

		if (c == 'L') {
			if (it != li.begin())it--;
		}
		else if (c == 'R') {
			if (it != li.end()) it++;

		}
		else if (c == 'D') {
			if (it != li.end()) it = li.erase(it);
		}
		else if (c == 'P') {
			char cc;
			cin >> cc;

			li.insert(it, cc);
		}
	}

	it = li.begin();
	while (it != li.end()) {
		cout << *it;
		it++;
	}
}