#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
vector<pair<int, int> > list;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N;
	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;

		list.push_back(make_pair(a, 1));	// ���� ���� ����
		list.push_back(make_pair(b, -1));	// ���� ���� ����
	}

	// ����
	sort(list.begin(), list.end());

	int cnt = 0;
	int ans = 0;
	int prev;	// ���� ���� ����
	for (auto it : list) {
		int x = it.first;
		int v = it.second;

		// ���� ������ ���
		if (v > 0) {
			// ���� ������ ���
			if (cnt == 0)prev = x;

			cnt++;
		}
		// ���� ������ ���
		else {
			cnt--;

			// ���� ���� ���
			if (cnt == 0) ans = MAX(ans, x - prev);
		}
	}

	cout << ans;
}