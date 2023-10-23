#include <iostream>
#include <stack>
#include <string>

using namespace std;

stack<int> st;
string s;

bool solve() {
	for (int i = 0; i < s.length(); i++) {
		char c = s[i];

		if (c == '(') st.push('(');
		else {
			if (st.empty()) return false;
			if (st.top() != '(') return false;

			st.pop();
		}
	}

	if (st.empty()) return true;
	return false;
}

int main() {

	cin >> s;

	bool ret = solve();

	if (ret) cout << "Yes";
	else cout << "No";
}