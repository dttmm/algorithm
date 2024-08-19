#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))
#define INF -2000000000

int N;
int K;
int cur;
vector<pair<int, int> > list;

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);

	cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int x;
		char c;
		cin >> x >> c;

		// �����Ǵ� ������ �������� ���� ���� ����
		if (c == 'R') {
			list.push_back(make_pair(cur, 1));

			cur += x;
			list.push_back(make_pair(cur, -1));
		}
		else {
			list.push_back(make_pair(cur, -1));

			cur -= x;
			list.push_back(make_pair(cur, 1));
		}
	}

	// ����
	sort(list.begin(), list.end());

	int ans = 0;
	int cnt = 0;
	int start = INF; // ���� ���� ����
	for (auto it : list) {
		cnt += it.second;

		// K�� �̻� ��ġ�� ��
		if (cnt >= K) {
			// K�� �̻� ��ġ�� ���� ����
			if (start == INF) start = it.first;
		}
		else {
			// K�� �̻� ��ġ�� ���� ����
			if (start != INF) ans += it.first - start;

			start = INF;
		}
	}

	cout << ans;
}