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

			// K�� �Ǿ��µ� ���� ���ڰ� ���ο� ������ ��� -> K+1�� �Ǿ����
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