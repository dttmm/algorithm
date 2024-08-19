#include <iostream>
#include <tuple>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

#define MAX_N 100000
#define MAX(a, b) ((a) > (b) ? (a) : (b))

int N;
int rooms[MAX_N + 1];
priority_queue<int> pq;
vector<tuple<int, int, int> > lists;

// �̿� ������ �� �ʱ�ȭ
void init() {
	for (int i = 1; i <= MAX_N; i++) {
		pq.push(-1 * i);
	}
}

int main() {

	init();

	cin >> N;
	for (int i = 0; i < N; i++) {
		int s, e;
		cin >> s >> e;

		lists.push_back(make_tuple(s - 1, 1, i));	// ���� ����, ���� ������ ���� ������ ��ġ�� ��� ó���ϱ� ���� -1
		lists.push_back(make_tuple(e, -1, i));	// ���� ����
	}

	// ����
	sort(lists.begin(), lists.end());

	int maxRoom = 0;	// �ִ� �� ��ȣ
	for (auto it : lists) {
		int x, v, id;
		tie(x, v, id) = it;

		// ���� ������ ���
		if (v > 0) {
			// ������ �� �Ҵ�
			int n = pq.top() * -1;
			pq.pop();

			rooms[id] = n;
			maxRoom = MAX(maxRoom, n);
		}
		// ���� ������ ���
		else {
			// �� �ݳ�
			int n = rooms[id];
			pq.push(-1 * n);
		}
	}

	cout << maxRoom;

}