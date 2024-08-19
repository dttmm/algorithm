#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

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

		list.push_back(make_pair(a, 1));	// ���� ����
		list.push_back(make_pair(b, -1));	// ���� ����
	}

	// ����
	sort(list.begin(), list.end());

	int ans = 0;	// �׷� ����
	int cnt = 0;	// ������� ���� ����
	for (auto it : list) {
		// ���������� ���
		if (it.second > 0) {

			// ù ������ ��� -> �׷� ����
			if (cnt == 0) ans++;

			cnt++;
		}
		// ���������� ���
		else {
			cnt--;
		}
	}

	cout << ans;
}