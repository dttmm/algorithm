#include <bits/stdc++.h>

using namespace std;

int K;
unordered_map<int, int> Map;

int main() {

	string str;
	cin >> str >> K;

	int ans = 0;
	int e = 0;
	for (int s = 0; s < str.length(); s++) {
		char sc = str[s] - 'a';

		while (e < str.length() && Map.size() <= K) {
			char ec = str[e] - 'a';

			// K개 되었는데 다음 문자가 새로운 문자일 경우 -> K+1개 되어버림
			if (Map.size() == K && Map.find(ec) == Map.end()) break;

			Map[ec]++;

			ans = max(ans, e - s + 1);

			e++;
		}

		Map[sc]--;
		if (Map[sc] == 0) Map.erase(sc);
	}

	cout << ans;
}