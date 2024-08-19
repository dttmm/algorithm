#include <bits/stdc++.h>

using namespace std;

bool arr[26];

int main() {

	string str;
	cin >> str;

	int ans = 0;
	int e = 0;
	for (int s = 0; s < str.length(); s++) {

		while (e < str.length() && !arr[str[e] - 'a']) {
			arr[str[e] - 'a'] = true;
			ans = max(ans, e - s);
			e++;
		}
		arr[str[s] - 'a'] = false;
	}

	cout << ans + 1;
}