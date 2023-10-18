#include <iostream>
#include <vector>
#include <string>

using namespace std;

int N;

int main() {

	vector<int> v;

	cin >> N;
	for (int i = 0; i < N; i++) {
		string s;
		int n;

		cin >> s;

		if (s == "push_back") {
			cin >> n;

			v.push_back(n);
		}
		else if (s == "pop_back") {
			v.pop_back();
		}
		else if (s == "size")
		{
			cout << v.size() << "\n";
		}
		else if (s == "get") {
			cin >> n;

			cout << v[n - 1] << "\n";
		}
	}
}